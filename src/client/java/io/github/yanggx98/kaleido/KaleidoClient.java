package io.github.yanggx98.kaleido;

import io.github.yanggx98.kaleido.render.tooltip.TooltipModule;
import net.fabricmc.api.ClientModInitializer;

public class KaleidoClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new TooltipModule().load();
    }
}