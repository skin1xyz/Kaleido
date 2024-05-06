package io.github.yanggx98.kaleido.mixin.client;

import io.github.yanggx98.kaleido.tooltip.LimitTooltipDrawer;
import io.github.yanggx98.kaleido.tooltip.api.*;
import io.github.yanggx98.kaleido.tooltip.components.ColorTooltipBorderComponent;
import io.github.yanggx98.kaleido.tooltip.components.TooltipComparatorProvider;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.item.TooltipData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {
    @Shadow
    protected abstract void drawTooltip(TextRenderer textRenderer, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner);

    @Inject(method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;Ljava/util/Optional;II)V", at = @At("HEAD"), cancellable = true)
    private void injectItemTooltip(TextRenderer textRenderer, List<Text> texts, Optional<TooltipData> data, int x, int y, CallbackInfo ci) {
        List<TooltipComponent> componentList = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            Text text = texts.get(i);
            ITooltipTextOverrideCallback.EVENT.invoker().override(text);
            TooltipComponent component = ITooltipComponentProviderCallback.EVENT.invoker().getTooltipComponent(text, i);
            if (component == null) {
                component = TooltipComponent.of(text.asOrderedText());
            }
            component = ITooltipComponentOverrideCallback.EVENT.invoker().overrideTooltipComponent(component, text, i);
            componentList.add(component);
        }
        ColorTooltipBorderComponent borderComponent = ITooltipBorderComponentProviderCallback.EVENT.invoker().getTooltipBorderComponent(texts);
        componentList.sort(TooltipComparatorProvider.getComparator());
        if (borderComponent != null) {
            componentList.add(0, borderComponent);
        }
        drawTooltip(textRenderer, componentList, x, y, HoveredTooltipPositioner.INSTANCE);
        ci.cancel();
    }


    @Inject(method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V", at = @At("HEAD"), cancellable = true)
    private void injectDrawTooltip(TextRenderer textRenderer, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner, CallbackInfo ci) {
        LimitTooltipDrawer.limitDrawTooltip((DrawContext) (Object) this, textRenderer, components, x, y, positioner);
        ci.cancel();
    }
}
