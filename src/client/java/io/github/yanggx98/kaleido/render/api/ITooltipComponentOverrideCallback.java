package io.github.yanggx98.kaleido.render.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.text.Text;

public interface ITooltipComponentOverrideCallback {

    Event<ITooltipComponentOverrideCallback> EVENT = EventFactory.createArrayBacked(ITooltipComponentOverrideCallback.class, callbacks -> (source, text, index) -> {
        for (ITooltipComponentOverrideCallback callback : callbacks) {
            return callback.overrideTooltipComponent(source, text, index);
        }
        return source;
    });

    TooltipComponent overrideTooltipComponent(TooltipComponent source, Text text, int index);
}
