package me.linoxgh.weaponryenhanced.api.settings;

import org.jetbrains.annotations.NotNull;

public class BooleanSetting extends ItemSetting<Boolean> {

    public BooleanSetting(@NotNull String key, boolean def) {
        super(key, def);
    }

    @Override
    public boolean isApplicable(@NotNull Object val) {
        return val instanceof Boolean;
    }
}
