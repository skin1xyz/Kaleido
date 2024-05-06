package io.github.yanggx98.kaleido.tooltip.components;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.OrderedText;
import net.minecraft.util.Identifier;
import org.joml.Math;
import org.joml.Matrix4f;

public class CombineTooltipComponent extends KaleidoTooltipComponent {
    private static final int ITEM_MODEL_SIZE = 16;
    private static final int SPACING = 4;
    private final TextureDescriptor descriptor;
    private OrderedText text;

    public CombineTooltipComponent(TextureDescriptor descriptor, OrderedText text, Padding padding) {
        super(padding);
        this.descriptor = descriptor;
        this.text = text;
    }


    @Override
    public int getLineHeight() {
        return Math.max(descriptor.textureHeight, 9);
    }


    @Override
    public int getLineWidth(TextRenderer textRenderer) {
        return textRenderer.getWidth(text) + SPACING + descriptor.textureHeight;
    }


    public int getTitleOffset() {
        return SPACING + descriptor.textureWith;
    }

    @Override
    public void drawTextInFrame(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        float startDrawX = (float) x + getTitleOffset();
        float startDrawY = y;
        textRenderer.draw(text, startDrawX, startDrawY, -1, true, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 0xF000F0);
    }

    @Override
    public void drawItemInFrame(TextRenderer textRenderer, int x, int y, DrawContext context) {
        context.drawTexture(descriptor.id, x, y, descriptor.u, descriptor.v, descriptor.width, descriptor.height, descriptor.textureWith, descriptor.textureHeight);
    }

    public static class TextureDescriptor {

        private final Identifier id;
        private final int u;
        private final int v;
        private final int width;
        private final int height;
        private final int textureWith;
        private final int textureHeight;

        public TextureDescriptor(Identifier id, int u, int v, int width, int height, int textureWith, int textureHeight) {
            this.id = id;
            this.u = u;
            this.v = v;
            this.width = width;
            this.height = height;
            this.textureWith = textureWith;
            this.textureHeight = textureHeight;
        }
    }
}
