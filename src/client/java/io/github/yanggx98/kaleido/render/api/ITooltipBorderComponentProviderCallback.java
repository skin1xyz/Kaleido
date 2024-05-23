package io.github.yanggx98.kaleido.render.api;

import io.github.yanggx98.kaleido.render.components.ColorTooltipBorderComponent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.text.Text;

import java.util.List;

public interface ITooltipBorderComponentProviderCallback {

    Event<ITooltipBorderComponentProviderCallback> EVENT = EventFactory.createArrayBacked(ITooltipBorderComponentProviderCallback.class, callbacks -> (texts) -> {
        for (ITooltipBorderComponentProviderCallback callback : callbacks) {
            return callback.getTooltipBorderComponent(texts);
        }
        return null;
    });

    ColorTooltipBorderComponent getTooltipBorderComponent(List<Text> text);
}
