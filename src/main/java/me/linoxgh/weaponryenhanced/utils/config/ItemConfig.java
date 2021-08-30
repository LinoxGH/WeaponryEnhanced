package me.linoxgh.weaponryenhanced.utils.config;

import java.util.List;

import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.api.settings.ItemSetting;
import me.linoxgh.weaponryenhanced.items.ItemList;
import me.linoxgh.weaponryenhanced.api.settings.BooleanSetting;
import me.linoxgh.weaponryenhanced.api.settings.DoubleSetting;
import me.linoxgh.weaponryenhanced.api.settings.IntegerSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemListSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemStackSetting;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemConfig {
    private final FileConfiguration cfg;
    private final ItemList itemList;

    public ItemConfig(@NotNull FileConfiguration cfg, @NotNull ItemList itemList) {
        this.cfg = cfg;
        this.itemList = itemList;
    }

    public void loadItemSettings() {
        ConfigurationSection items = cfg.getConfigurationSection("items");
        if (items == null) items = cfg.createSection("items");

        for (ItemWithHandler<? extends ItemHandler> item : itemList.getItems()) {
            String id = item.getId();
            ConfigurationSection itemCfg = items.getConfigurationSection(id);
            if (itemCfg == null) itemCfg = items.createSection(id);

            for (ItemSetting<?> setting : item.getSettings()) {
                String key = setting.getKey();
                Object value = itemCfg.get(key);
                if (value == null) {
                    itemCfg.set(key, setting.getDefaultValue());
                    continue;
                }

                if (setting instanceof BooleanSetting && value instanceof Boolean) {
                    ((BooleanSetting) setting).setValue((boolean) value);
                } else if (setting instanceof DoubleSetting && value instanceof Double) {
                    ((DoubleSetting) setting).setValue((double) value);
                } else if (setting instanceof IntegerSetting && value instanceof Integer) {
                    ((IntegerSetting) setting).setValue((int) value);
                } else if (setting instanceof ItemListSetting && value instanceof List) {
                    ((ItemListSetting) setting).setValue((List<ItemStack>) value);
                } else if (setting instanceof ItemStackSetting && value instanceof ItemStack) {
                    ((ItemStackSetting) setting).setValue((ItemStack) value);
                }
            }
        }
    }
}
