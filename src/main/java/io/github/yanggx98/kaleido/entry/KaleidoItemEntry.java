package io.github.yanggx98.kaleido.entry;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public abstract class KaleidoItemEntry<T> {
    protected T value;
    protected String id;
    protected Style style;

    public KaleidoItemEntry(String id, Style style, T value) {
        this.id = id;
        this.style = style;
        this.value = value;
    }

    public Text getText() {
        return Text.translatable(id, value).setStyle(style);
    }

    public abstract void setValue(NbtCompound nbtCompound);

    public void setValue(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract void saveValue(NbtCompound nbtCompound);
}
