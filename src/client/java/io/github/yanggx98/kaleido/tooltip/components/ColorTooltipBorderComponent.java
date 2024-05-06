package io.github.yanggx98.kaleido.tooltip.components;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;

public class ColorTooltipBorderComponent implements TooltipComponent {
    private int color;
    protected static final int INNER_PADDING = 5;

    public ColorTooltipBorderComponent(int color) {
        this.color = color;
    }

    public void drawBorder(DrawContext context, int x, int y, int width, int height, int z, int p) {
        int i = x - INNER_PADDING;
        int j = y - INNER_PADDING;
        int k = width + INNER_PADDING*2;
        int l = height + INNER_PADDING*2;
        renderHorizontalLine(context, i, j - 1, k, z, -267386864);
        renderHorizontalLine(context, i, j + l, k, z, -267386864);
        renderRectangle(context, i, j, k, l, z, -267386864);
        renderVerticalLine(context, i - 1, j, l, z, -267386864);
        renderVerticalLine(context, i + k, j, l, z, -267386864);
        renderBorder(context, i, j + 1, k, l, z, color, 1344798847);
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 0;
    }

    private static void renderBorder(DrawContext context, int x, int y, int width, int height, int z, int startColor, int endColor) {
        renderVerticalLine(context, x, y, height - 2, z, startColor, endColor);
        renderVerticalLine(context, x + width - 1, y, height - 2, z, startColor, endColor);
        renderHorizontalLine(context, x, y - 1, width, z, startColor);
        renderHorizontalLine(context, x, y - 1 + height - 1, width, z, endColor);
    }

    private static void renderVerticalLine(DrawContext context, int x, int y, int height, int z, int color) {
        context.fill(x, y, x + 1, y + height, z, color);
    }

    private static void renderVerticalLine(DrawContext context, int x, int y, int height, int z, int startColor, int endColor) {
        context.fillGradient(x, y, x + 1, y + height, z, startColor, endColor);
    }

    private static void renderHorizontalLine(DrawContext context, int x, int y, int width, int z, int color) {
        context.fill(x, y, x + width, y + 1, z, color);
    }

    private static void renderRectangle(DrawContext context, int x, int y, int width, int height, int z, int color) {
        context.fill(x, y, x + width, y + height, z, color);
    }

//    public static class TooltipBorder {
//        private final Identifier texture;
//        private final int u;
//        private final int v;
//        private final int width;
//        private final int height;
//
//        public TooltipBorder(Identifier texture, int u, int v, int width, int height) {
//            this.texture = texture;
//            this.u = u;
//            this.v = v;
//            this.width = width;
//            this.height = height;
//        }
//
//    }

}
