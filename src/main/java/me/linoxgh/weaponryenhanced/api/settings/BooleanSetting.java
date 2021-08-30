package me.linoxgh.weaponryenhanced.utils.config.settings;

import me.linoxgh.weaponryenhanced.utils.config.ItemSetting;
import org.jetbrains.annotations.NotNull;

public class BooleanSetting extends ItemSetting<Boolean> {

    public BooleanSetting(@NotNull String key, boolean def) {
        super(key, def);
    }
}
