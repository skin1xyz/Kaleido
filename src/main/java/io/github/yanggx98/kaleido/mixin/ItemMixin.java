package io.github.yanggx98.kaleido.mixin;

import io.github.yanggx98.kaleido.entry.KaleidoItemEntry;
import io.github.yanggx98.kaleido.entry.KaleidoEntryHelper;
import io.github.yanggx98.kaleido.entry.IKaleidoItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

import static io.github.yanggx98.kaleido.entry.api.IKaleidoItemEntryProvider.EVENT;

@Mixin(Item.class)
public class ItemMixin implements IKaleidoItem {
    @Override
    public List<KaleidoItemEntry<?>> kaleido$getKaleidoEntries(ItemStack itemStack) {
        return EVENT.invoker().kaleido$getKaleidoEntries(itemStack);
    }
}
