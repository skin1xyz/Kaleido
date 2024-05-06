package io.github.yanggx98.kaleido.tooltip.components;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import org.joml.Matrix4f;

public abstract class KaleidoTooltipComponent implements TooltipComponent {
    private final Padding padding;

    public KaleidoTooltipComponent(Padding padding) {
        this.padding = padding;
    }

    @Override
    public int getHeight() {
        return getLineHeight() + padding.getVerticalHeight();
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return getLineWidth(textRenderer) + padding.getHorizontalWidth();
    }

    @Override
    public final void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        drawItemInFrame(textRenderer, x + padding.start, y + padding.top, context);
    }


    @Override
    public final void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        drawTextInFrame(textRenderer, x + padding.start, y + padding.top, matrix, vertexConsumers);
    }


    public abstract int getLineHeight();

    public abstract int getLineWidth(TextRenderer textRenderer);


    public void drawTextInFrame(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {

    }

    public void drawItemInFrame(TextRenderer textRenderer, int x, int y, DrawContext context) {

    }


    public static class Padding {
        public static final Padding NONE = of(0, 0, 0, 0);
        public static final Padding CHILD = of(8, 0, 0, 0);
        final int start;
        final int end;
        final int top;
        final int bottom;

        public static Padding of(int start, int end, int top, int bottom) {
            return new Padding(start, end, top, bottom);
        }

        private Padding(int start, int end, int top, int bottom) {
            this.start = start;
            this.end = end;
            this.top = top;
            this.bottom = bottom;
        }


        public int getVerticalHeight() {
            return bottom + top;
        }

        public int getHorizontalWidth() {
            return start + end;
        }
    }
}
