package io.github.yanggx98.kaleido.entry;

import io.github.yanggx98.kaleido.entry.api.IKaleidoItemEntryFactory;
import net.minecraft.util.Identifier;

public class KaleidoItemEntryCreator<V, T extends KaleidoItemEntry<V>> {
    private IKaleidoItemEntryFactory<V, T> factory;
    private Identifier id;

    public KaleidoItemEntryCreator(Identifier id, IKaleidoItemEntryFactory<V, T> factory) {
        this.id = id;
        this.factory = factory;
    }

    public KaleidoItemEntry<V> create(V value) {
        KaleidoItemEntry<V> entry = factory.createEntry(id.toTranslationKey());
        entry.setValue(value);
        return entry;
    }

    public Identifier getId() {
        return id;
    }

}
