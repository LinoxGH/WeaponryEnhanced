package me.linoxgh.weaponryenhanced.api.settings;

import org.jetbrains.annotations.NotNull;

public class IntegerSetting extends ItemSetting<Integer> {

    public IntegerSetting(@NotNull String key, @NotNull Integer def) {
        super(key, def);
    }

    @Override
    public boolean isApplicable(@NotNull Object val) {
        return val instanceof Integer;
    }
}
