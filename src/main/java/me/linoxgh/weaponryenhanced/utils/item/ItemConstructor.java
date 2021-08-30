package me.linoxgh.weaponryenhanced.utils.item;

import java.util.Arrays;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemConstructor {
    private final ItemStack result;

    public ItemConstructor(@NotNull Material type, @NotNull Component name, @Nullable Component... lore) {
        result = new ItemStack(type);
        ItemMeta im = result.getItemMeta();
        im.displayName(name);
        im.lore(Arrays.asList(lore));
        result.setItemMeta(im);
    }

    public ItemStack construct() {
        return result;
    }
}
