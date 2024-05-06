package io.github.yanggx98.kaleido.entry;

import io.github.yanggx98.kaleido.entry.api.IKaleidoItemEntryFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.List;

public class KaleidoEntryHelper {
    private static final String NBT_KEY = "kaleido.entries.nbt";

    public static void addEntry(ItemStack itemStack, KaleidoItemEntry<?> entry) {
        NbtCompound nbtCompound = itemStack.getNbt();
        if (nbtCompound == null) {
            nbtCompound = new NbtCompound();
            itemStack.setNbt(nbtCompound);
        }
        NbtCompound kaleidoNbt = nbtCompound.getCompound(NBT_KEY);
        if (kaleidoNbt.isEmpty()) {
            kaleidoNbt = new NbtCompound();
            nbtCompound.put(NBT_KEY, kaleidoNbt);
        }
        entry.saveValue(kaleidoNbt);
    }

    public static List<KaleidoItemEntry<?>> getEntriesFromNbt(ItemStack itemStack) {
        ArrayList<KaleidoItemEntry<?>> attributes = new ArrayList<>();
        NbtCompound nbtCompound = itemStack.getNbt();
        if (nbtCompound == null) {
            return List.of();
        }
        if (nbtCompound.contains(NBT_KEY)) {
            NbtCompound kaleidoNbt = nbtCompound.getCompound(NBT_KEY);
            for (String key : KaleidoItemEntryRegistry.ENTRY_MAP.keySet()) {
                if (kaleidoNbt.contains(key)) {
                    IKaleidoItemEntryFactory<?,?> provider = KaleidoItemEntryRegistry.get(key);
                    KaleidoItemEntry<?> attribute = provider.createEntry(key);
                    if (attribute != null) {
                        attribute.setValue(kaleidoNbt);
                        attributes.add(attribute);
                    }
                }
            }
        }
        return attributes;
    }
}
