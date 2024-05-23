package io.github.yanggx98.kaleido.render.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.text.Text;

public interface ITooltipTextOverrideCallback {
    /**
     * Fired after the game has appended all base tooltip lines to the list.
     */
    Event<ITooltipTextOverrideCallback> EVENT = EventFactory.createArrayBacked(ITooltipTextOverrideCallback.class, callbacks -> (text) -> {
        for (ITooltipTextOverrideCallback callback : callbacks) {
            callback.override(text);
        }
    });

    void override(Text text);
}
