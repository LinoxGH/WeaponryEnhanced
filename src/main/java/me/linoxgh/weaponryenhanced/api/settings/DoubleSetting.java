package me.linoxgh.weaponryenhanced.api.settings;

import org.jetbrains.annotations.NotNull;

public class DoubleSetting extends ItemSetting<Double> {

    public DoubleSetting(@NotNull String key, @NotNull Double def) {
        super(key, def);
    }
}
