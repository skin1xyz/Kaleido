package io.github.yanggx98.kaleido.render.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipData;
import net.minecraft.text.Text;

public class TooltipComponentAPIs {
    private static TooltipComponentProvider DEFAULT_TOOLTIP_COMPONENT_PROVIDER;
    public static final Event<Overrider> OVERRIDE_EVENT =
            EventFactory.createArrayBacked(Overrider.class, callbacks -> (source,index) -> {
                TooltipComponent component = source;
                for (Overrider callback : callbacks) {
                    component = callback.overrider(source,index);
                }
                return component;
    });
    public static final Event<Provider> PROVIDER_EVENT =
            EventFactory.createArrayBacked(Provider.class, callbacks -> (text,index) -> {
                TooltipComponent component = null;
                for (Provider callback : callbacks) {
                    component = callback.getComponent(text,index);
                }
                return component;
            });
    public interface Overrider{
         TooltipComponent overrider(TooltipComponent source,int index);
    }
    public interface Provider{
        TooltipComponent getComponent(Text text,int index);
    }
    public interface TooltipComponentProvider{
        TooltipComponent of(Text text);
        TooltipComponent of(TooltipData data);
    }
    public static void setDefaultTooltipComponentProvider(TooltipComponentProvider provider){
        DEFAULT_TOOLTIP_COMPONENT_PROVIDER = provider;
    }

    public static TooltipComponentProvider getDefaultTooltipComponentProvider(){
        return DEFAULT_TOOLTIP_COMPONENT_PROVIDER;
    }
}
