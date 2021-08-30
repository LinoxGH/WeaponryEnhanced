package me.linoxgh.weaponryenhanced.utils.config;

import org.jetbrains.annotations.NotNull;

public abstract class ItemSetting<T> {
    private final String KEY;
    private final T DEFAULT;
    private T value;

    public ItemSetting(@NotNull String key, @NotNull T def) {
        this.KEY = key;
        this.DEFAULT = def;
        this.value = def;
    }

    public @NotNull String getKey() {
        return KEY;
    }
    public @NotNull T getDefaultValue() {
        return DEFAULT;
    }

    public @NotNull T getValue() {
        return value;
    }
    public void setValue(@NotNull T VALUE) {
        this.value = VALUE;
    }
}
