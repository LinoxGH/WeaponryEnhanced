package me.linoxgh.weaponryenhanced.utils.config.settings;

import me.linoxgh.weaponryenhanced.utils.config.ItemSetting;
import org.jetbrains.annotations.NotNull;

public class IntegerSetting extends ItemSetting<Integer> {

    public IntegerSetting(@NotNull String key, @NotNull Integer def) {
        super(key, def);
    }
}
