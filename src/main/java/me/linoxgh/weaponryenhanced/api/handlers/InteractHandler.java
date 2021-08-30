package me.linoxgh.weaponryenhanced.api.handlers;

import org.bukkit.event.player.PlayerInteractEvent;

@FunctionalInterface
public interface InteractHandler extends ItemHandler {

    void onUse(PlayerInteractEvent e);
}
