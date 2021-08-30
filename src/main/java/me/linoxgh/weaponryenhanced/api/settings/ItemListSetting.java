package me.linoxgh.weaponryenhanced.api.settings;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemListSetting extends ItemSetting<List<ItemStack>> {

    public ItemListSetting(@NotNull String key, @NotNull List<ItemStack> def) {
        super(key, def);
    }
}
