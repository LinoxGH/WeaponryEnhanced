package me.linoxgh.weaponryenhanced.items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemList {
    private final Set<ItemWithHandler<? extends ItemHandler>> items = new HashSet<>();
    private final Map<String, ItemWithHandler<? extends ItemHandler>> ids = new HashMap<>();

    public @NotNull Set<ItemWithHandler<? extends ItemHandler>> getItems() {
        return items;
    }
    public @NotNull Map<String, ItemWithHandler<? extends ItemHandler>> getIds() {
        return ids;
    }

    public void addItem(@NotNull ItemWithHandler<? extends ItemHandler> item) {
        items.add(item);
        ids.put(item.getId(), item);
    }

    public @Nullable ItemWithHandler<? extends ItemHandler> getItem(@NotNull String id) {
        return ids.get(id);
    }
}
