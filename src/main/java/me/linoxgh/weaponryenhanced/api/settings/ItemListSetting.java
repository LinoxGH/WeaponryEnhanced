package me.linoxgh.weaponryenhanced.api.settings;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemListSetting extends ItemSetting<List<ItemStack>> {

    public ItemListSetting(@NotNull String key, @NotNull List<ItemStack> def) {
        super(key, def);
    }

    @Override
    public boolean isApplicable(@NotNull Object val) {
        int check = 0;
        if (!(val instanceof List)) return false;
        for (Object entry : (List<?>) val) {
            if (entry instanceof ItemStack) check++;
        }
        return check == ((List<?>) val).size();
    }
}
