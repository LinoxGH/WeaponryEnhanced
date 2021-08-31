package me.linoxgh.weaponryenhanced.api.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface InteractHandler extends ItemHandler {

    void onUse(@NotNull PlayerInteractEvent e, @NotNull Player p, @NotNull ItemStack item);
}
