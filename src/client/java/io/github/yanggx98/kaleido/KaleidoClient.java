package io.github.yanggx98.kaleido;

import io.github.yanggx98.kaleido.entries.KaleidoEntryTooltipHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class KaleidoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
			lines.addAll(KaleidoEntryTooltipHelper.getKaleidoEntryTooltips(stack));
		});
	}
}