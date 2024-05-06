package io.github.yanggx98.kaleido.entries;

import io.github.yanggx98.kaleido.entry.IKaleidoItem;
import io.github.yanggx98.kaleido.entry.KaleidoItemEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class KaleidoEntryTooltipHelper {
    public static List<Text> getKaleidoEntryTooltips(ItemStack stack) {
        if (stack.getItem() instanceof IKaleidoItem item) {
            return item.kaleido$getKaleidoEntries(stack).stream().map(KaleidoItemEntry::getText).toList();
        }
        return List.of();
    }

}
