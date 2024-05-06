package io.github.yanggx98.kaleido.entry;

import io.github.yanggx98.kaleido.entry.api.IKaleidoItemEntryFactory;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class KaleidoItemEntryRegistry {
    public static KaleidoItemEntryRegistry INSTANCE = new KaleidoItemEntryRegistry();
    static final Map<String, IKaleidoItemEntryFactory<?, ?>> ENTRY_MAP = new HashMap<>();

    public <V, T extends KaleidoItemEntry<V>> KaleidoItemEntryCreator<V, T> register(Identifier identifier, IKaleidoItemEntryFactory<V, T> factory) {
        if (ENTRY_MAP.get(identifier.toTranslationKey()) != null) {
            throw new IllegalArgumentException("multiple definition of \"" + identifier.toTranslationKey() + "\"");
        }
        ENTRY_MAP.put(identifier.toTranslationKey(), factory);
        return new KaleidoItemEntryCreator<V,T>(identifier, factory);
    }

    static IKaleidoItemEntryFactory<?, ?> get(String key) {
        return ENTRY_MAP.get(key);
    }
}
