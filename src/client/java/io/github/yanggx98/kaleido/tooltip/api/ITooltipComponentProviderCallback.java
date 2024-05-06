package io.github.yanggx98.kaleido.tooltip.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public interface ITooltipComponentProviderCallback {
    Event<ITooltipComponentProviderCallback> EVENT = EventFactory.createArrayBacked(ITooltipComponentProviderCallback.class, callbacks -> (text, index) -> {
        TooltipComponent component = null;
        for (ITooltipComponentProviderCallback callback : callbacks) {
            component = callback.getTooltipComponent(text, index);
            if (component != null) {
                return component;
            }
        }
        return null;
    });

    TooltipComponent getTooltipComponent(Text text, int index);
}
