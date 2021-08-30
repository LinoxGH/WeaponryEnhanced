package me.linoxgh.weaponryenhanced.items;

import java.util.HashSet;
import java.util.Set;

import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.jetbrains.annotations.NotNull;

public class ItemList {
    private final Set<ItemWithHandler<? extends ItemHandler>> items = new HashSet<>();

    public @NotNull Set<ItemWithHandler<? extends ItemHandler>> getItems() {
        return items;
    }

    public void addItem(@NotNull ItemWithHandler<? extends ItemHandler> item) {
        items.add(item);
    }
}
