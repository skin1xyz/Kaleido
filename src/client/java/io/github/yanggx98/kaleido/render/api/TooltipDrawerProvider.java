package io.github.yanggx98.kaleido.render.api;

import io.github.yanggx98.kaleido.render.LimitTooltipDrawer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipPositioner;

public class TooltipDrawerProvider {
    private static ITooltipDrawer ITooltipDrawer = null;


    public interface ITooltipDrawer {
        void drawTooltip(DrawContext context, TextRenderer textRenderer, int x, int y, TooltipPositioner positioner);
    }

    public static void setTooltipDrawerProvider(ITooltipDrawer provider){
        ITooltipDrawer = provider;
    }
    public static ITooltipDrawer getTooltipDrawerProvider(){
        return ITooltipDrawer;
    }
}
