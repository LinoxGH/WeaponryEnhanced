package me.linoxgh.weaponryenhanced.utils.config.settings;

import java.util.List;

import me.linoxgh.weaponryenhanced.utils.config.ItemSetting;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemListSetting extends ItemSetting<List<ItemStack>> {

    public ItemListSetting(@NotNull String key, @NotNull List<ItemStack> def) {
        super(key, def);
    }
}
