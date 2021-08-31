package me.linoxgh.weaponryenhanced.utils.item;

import java.util.Arrays;

import me.linoxgh.weaponryenhanced.WeaponryEnhanced;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemConstructor {
    private final ItemStack result;

    public ItemConstructor(@NotNull WeaponryEnhanced plugin, @NotNull String id, @NotNull Material type, @NotNull Component name, @Nullable Component... lore) {
        result = new ItemStack(type);
        ItemMeta im = result.getItemMeta();
        im.displayName(name);
        im.lore(Arrays.asList(lore));
        im.getPersistentDataContainer().set(new NamespacedKey(plugin, "id"), PersistentDataType.STRING, id);
        result.setItemMeta(im);
    }

    public ItemStack construct() {
        return result;
    }
}
