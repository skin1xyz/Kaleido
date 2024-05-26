package io.github.yanggx98.kaleido.render.tooltip.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class TooltipMark {
    public static String MARK_KEY = "kaleido_tooltip_mark";

    public static Text createItemMark(ItemStack stack) {
        return Text.of(MARK_KEY);
    }

}
