package io.github.yanggx98.kaleido.entry;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IKaleidoItem {
    List<KaleidoItemEntry<?>> kaleido$getKaleidoEntries(ItemStack itemStack);
}
