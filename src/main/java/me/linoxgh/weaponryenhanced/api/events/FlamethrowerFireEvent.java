package me.linoxgh.weaponryenhanced.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FlamethrowerFireEvent extends ItemUseEvent implements Cancellable {
    private final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private final ItemStack item;
    private boolean fire;
    private int amount;
    private double range;
    private ItemStack ammo;

    public FlamethrowerFireEvent(@NotNull Player p, @NotNull ItemStack item, boolean fire, int amount, double range, @NotNull ItemStack ammo) {
        super(p);
        this.item = item;
        this.fire = fire;
        this.amount = amount;
        this.range = range;
        this.ammo = ammo;
    }

    public @NotNull ItemStack getItem() {
        return item;
    }
    public boolean isFire() {
        return fire;
    }
    public int getAmount() {
        return amount;
    }
    public double getRange() {
        return range;
    }
    public @NotNull ItemStack getAmmo() {
        return ammo;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setRange(double range) {
        this.range = range;
    }
    public void setAmmo(@NotNull ItemStack ammo) {
        this.ammo = ammo;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
