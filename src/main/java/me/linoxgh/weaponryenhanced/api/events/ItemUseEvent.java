package me.linoxgh.weaponryenhanced.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public abstract class ItemUseEvent extends PlayerEvent {

    public ItemUseEvent(@NotNull Player who) {
        super(who);
    }
}
