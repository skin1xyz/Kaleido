package io.github.yanggx98.kaleido.entry.api;

import io.github.yanggx98.kaleido.entry.KaleidoItemEntry;
import net.minecraft.item.ItemStack;

public interface IKaleidoItemEntryFactory<V,T extends KaleidoItemEntry<V>> {
    T createEntry(String id);
}
