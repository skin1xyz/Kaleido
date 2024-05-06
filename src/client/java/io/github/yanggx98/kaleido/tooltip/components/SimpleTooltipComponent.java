package io.github.yanggx98.kaleido.tooltip.components;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.OrderedText;
import org.joml.Matrix4f;

public class SimpleTooltipComponent extends KaleidoTooltipComponent {
    private static final int LINE_HEIGHT = 12;
    private OrderedText text;

    public SimpleTooltipComponent(OrderedText text, Padding padding) {
        super(padding);
        this.text = text;
    }

    @Override
    public int getLineHeight() {
        return LINE_HEIGHT;
    }

    @Override
    public int getLineWidth(TextRenderer textRenderer) {
        return textRenderer.getWidth(text);
    }

    @Override
    public void drawTextInFrame(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        textRenderer.draw(text, x, y+ (float) (getLineHeight() - textRenderer.fontHeight) / 2, -1, true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 0xF000F0);
    }

    @Override
    public void drawItemInFrame(TextRenderer textRenderer, int x, int y, DrawContext context) {

    }
}
