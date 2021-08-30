package me.linoxgh.weaponryenhanced.utils.config.settings;

import me.linoxgh.weaponryenhanced.utils.config.ItemSetting;
import org.jetbrains.annotations.NotNull;

public class DoubleSetting extends ItemSetting<Double> {

    public DoubleSetting(@NotNull String key, @NotNull Double def) {
        super(key, def);
    }
}
