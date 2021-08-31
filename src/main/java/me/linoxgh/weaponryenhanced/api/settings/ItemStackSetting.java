package me.linoxgh.weaponryenhanced.api.settings;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackSetting extends ItemSetting<ItemStack> {

    public ItemStackSetting(@NotNull String key, @NotNull ItemStack def) {
        super(key, def);
    }

    @Override
    public boolean isApplicable(@NotNull Object val) {
        return val instanceof ItemStack;
    }
}
