package me.linoxgh.weaponryenhanced.listeners;

import me.linoxgh.weaponryenhanced.WeaponryEnhanced;
import me.linoxgh.weaponryenhanced.api.handlers.InteractHandler;
import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.items.ItemList;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ItemListener implements Listener {
    private final WeaponryEnhanced plugin;
    private final ItemList itemList;

    public ItemListener(@NotNull WeaponryEnhanced plugin, @NotNull ItemList itemList) {
        this.plugin = plugin;
        this.itemList = itemList;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null) return;
        ItemMeta im = item.getItemMeta();
        if (im == null) return;
        String id = im.getPersistentDataContainer().get(new NamespacedKey(plugin, "id"), PersistentDataType.STRING);
        if (id == null) return;
        ItemWithHandler<? extends ItemHandler> ih = itemList.getItem(id);
        if (ih == null) return;
        for (ItemHandler handler : ih.getHandlers()) {
            if (handler instanceof InteractHandler) {
                ((InteractHandler) handler).onUse(e, e.getPlayer(), item);
            }
        }
    }
}
