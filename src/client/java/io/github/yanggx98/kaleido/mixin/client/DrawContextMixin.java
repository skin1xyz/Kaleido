package io.github.yanggx98.kaleido.mixin.client;

import io.github.yanggx98.kaleido.render.tooltip.api.TooltipComponentAPI;
import io.github.yanggx98.kaleido.render.tooltip.api.TooltipDrawerProvider;
import io.github.yanggx98.kaleido.render.tooltip.impl.TooltipItemStackCache;
import io.github.yanggx98.kaleido.render.tooltip.impl.TooltipMark;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.client.gui.tooltip.OrderedTextTooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {
    @Inject(method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V", at = @At("HEAD"), cancellable = true)
    private void injectDrawTooltip(TextRenderer textRenderer, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner, CallbackInfo ci) {
        TooltipComponent markComponent = null;
        for (TooltipComponent component : components) {
            if (component instanceof OrderedTextTooltipComponent) {
                String content = getContent(((ITextContentProvider) component).getText());
                if (TooltipMark.MARK_KEY.equals(content)) {
                    markComponent = component;
                }
            }
        }
        if (markComponent != null) {
            components.remove(markComponent);
            ItemStack cacheItemStack = TooltipItemStackCache.getItemStack();
            if (cacheItemStack != null) {
                TooltipComponentAPI.EVENT.invoker().of(components, cacheItemStack);
                TooltipDrawerProvider.ITooltipDrawer drawer = TooltipDrawerProvider.getTooltipDrawer();
                if (drawer != null) {
                    drawer.drawTooltip((DrawContext) (Object) this, textRenderer, components, x, y, HoveredTooltipPositioner.INSTANCE);
                    ci.cancel();
                }
            }
        }
    }


    @Unique
    public String getContent(OrderedText text) {
        try {
            Class clazz = text.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(text) instanceof String content) {
                    return content;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
