package me.linoxgh.weaponryenhanced.api.settings;

import org.jetbrains.annotations.NotNull;

public class EnumSetting<T extends Enum<T>> extends ItemSetting<T> {
    private final Class<T> enumClass;

    public EnumSetting(@NotNull Class<T> enumClass, @NotNull String key, @NotNull T def) {
        super(key, def);
        this.enumClass = enumClass;
    }

    public @NotNull Class<T> getEnumClass() {
        return enumClass;
    }

    private @NotNull T[] getAllowedValues() {
        return enumClass.getEnumConstants();
    }

    @Override
    public boolean isApplicable(@NotNull Object val) {
        if (!(val instanceof String)) return false;
        for (T allowedValue : getAllowedValues()) {
            if (allowedValue.name().equals(val)) return true;
        }
        return false;
    }
}
