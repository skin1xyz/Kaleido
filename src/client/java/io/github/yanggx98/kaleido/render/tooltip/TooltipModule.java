package io.github.yanggx98.kaleido.render.tooltip;

import io.github.yanggx98.kaleido.IKaleidoModule;
import io.github.yanggx98.kaleido.render.tooltip.impl.TooltipItemStackCache;
import io.github.yanggx98.kaleido.render.tooltip.impl.TooltipMark;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class TooltipModule implements IKaleidoModule {

    @Override
    public void load() {
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
            TooltipItemStackCache.saveItemStack(stack);
            lines.add(TooltipMark.createItemMark(stack));
        });
    }
}
