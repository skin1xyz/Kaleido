package io.github.yanggx98.kaleido.entry.api;

import io.github.yanggx98.kaleido.entry.KaleidoItemEntry;
import io.github.yanggx98.kaleido.entry.IKaleidoItem;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IKaleidoItemEntryProvider<T extends KaleidoItemEntry<?>> extends IKaleidoItem {
    Event<IKaleidoItemEntryProvider<?>> EVENT = EventFactory.createArrayBacked(IKaleidoItemEntryProvider.class, callbacks -> (stack) -> {
        for (IKaleidoItemEntryProvider<?> callback : callbacks) {
            return callback.kaleido$getKaleidoEntries(stack);
        }
        return List.of();
    });

}
