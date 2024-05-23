package io.github.yanggx98.kaleido.render;

import io.github.yanggx98.kaleido.render.components.ColorTooltipBorderComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Vector2ic;

import java.util.ArrayList;
import java.util.List;

public final class LimitTooltipDrawer {
    private static final int EDGE_SPACING = 32;
    private static final int PAGE_SPACING = 12;

    private static int getLimitMaxHeight() {
        return MinecraftClient.getInstance().getWindow().getScaledHeight() - EDGE_SPACING * 2;
    }

    private static class TooltipPage {
        private int x;
        private int y;
        private int width;
        private int height;
        private List<TooltipComponent> components;

        private TooltipPage() {
            this(0, 0, 0, 0, new ArrayList<>());
        }

        private TooltipPage(int x, int y, int width, int height, List<TooltipComponent> components) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.components = components;
        }
    }

    public static void limitDrawTooltip(DrawContext context, TextRenderer textRenderer, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner) {
        MatrixStack matrices = context.getMatrices();
        ColorTooltipBorderComponent borderComponent = null;
        List<TooltipPage> pages = new ArrayList<>();
        if (components.isEmpty()) {
            return;
        }
        for (TooltipComponent component : components) {
            if (component instanceof ColorTooltipBorderComponent) {
                borderComponent = (ColorTooltipBorderComponent) component;
                break;
            }
        }

        if (borderComponent != null) {
            components.remove(borderComponent);
        }

        int i = 0;
        int j = components.size() == 1 ? -2 : 0;
//        int j =  0;
        int limitHeight = getLimitMaxHeight();
        TooltipPage page = new TooltipPage();
        int totalWidth = 0;


        // 如果有两个以上的项目则需要给第一个空出空间
        int spacing = components.size() > 1 ? 4 : 0;
        j += spacing;
        for (int g = 0; g < components.size(); g++) {
            TooltipComponent tooltipComponent = components.get(g);
            int k = tooltipComponent.getWidth(textRenderer);
            if (k > i) {
                i = k;
            }
            j += tooltipComponent.getHeight();
            if (j > limitHeight) {
                pages.add(page);
                totalWidth += page.width;
                page = new TooltipPage();
                page.components.add(tooltipComponent);
                page.height = tooltipComponent.getHeight();
                i = tooltipComponent.getWidth(textRenderer);
                j = tooltipComponent.getHeight();
                if (g == components.size() - 1) {
                    page.width = tooltipComponent.getWidth(textRenderer);
                    pages.add(page);
                    totalWidth += page.width;
                }
            } else if (g == components.size() - 1) {
                page.height = j;
                page.width = i;
                page.components.add(tooltipComponent);
                pages.add(page);
                totalWidth += page.width;
            } else {
                page.height = j;
                page.width = i;
                page.components.add(tooltipComponent);
            }
        }

        Vector2ic vector2ic = positioner.getPosition(context.getScaledWindowWidth(), context.getScaledWindowHeight(), x, y, totalWidth, pages.get(0).height);
        int n = vector2ic.x();
        int o = vector2ic.y();

        for (TooltipPage tooltipPage : pages) {
            tooltipPage.x = n;
            tooltipPage.y = (pages.size() > 1) ? o - EDGE_SPACING : o - 6;
            n += tooltipPage.width + PAGE_SPACING;
        }

        matrices.push();

        for (TooltipPage p : pages) {
            if (borderComponent == null) {
                context.draw(() -> TooltipBackgroundRenderer.render(context, p.x, p.y, p.width, p.height, 400));
            } else {
                ColorTooltipBorderComponent finalBorderComponent = borderComponent;
                context.draw(() -> finalBorderComponent.drawBorder(context, p.x, p.y, p.width, p.height, 400, pages.indexOf(p)))
                ;
            }
        }

        matrices.translate(0.0f, 0.0f, 400.0f);

        for (TooltipPage p : pages) {
            int cx = p.x;
            int cy = p.y;
            for (TooltipComponent component : p.components) {
                component.drawText(textRenderer, cx, cy, matrices.peek().getPositionMatrix(), context.getVertexConsumers());
                component.drawItems(textRenderer, cx, cy, context);
                cy += component.getHeight();
                if (p == pages.get(0) && component == p.components.get(0)) {
                    cy += spacing;
                }
            }
        }
        matrices.pop();
    }
}
