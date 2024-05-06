package io.github.yanggx98.kaleido.mixin;

import io.github.yanggx98.kaleido.entry.KaleidoEntryHelper;
import io.github.yanggx98.kaleido.entry.KaleidoItemEntry;
import io.github.yanggx98.kaleido.entry.IKaleidoItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin implements IKaleidoItem {
    @Override
    public List<KaleidoItemEntry<?>> kaleido$getKaleidoEntries(ItemStack itemStack) {
        return KaleidoEntryHelper.getEntriesFromNbt(itemStack);
    }
}
