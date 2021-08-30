package me.linoxgh.weaponryenhanced.utils.config.settings;

import me.linoxgh.weaponryenhanced.utils.config.ItemSetting;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackSetting extends ItemSetting<ItemStack> {

    public ItemStackSetting(@NotNull String key, @NotNull ItemStack def) {
        super(key, def);
    }
}
