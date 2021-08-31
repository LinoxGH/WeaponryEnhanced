package me.linoxgh.weaponryenhanced.utils.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.linoxgh.weaponryenhanced.WeaponryEnhanced;
import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.api.settings.BooleanSetting;
import me.linoxgh.weaponryenhanced.api.settings.DoubleSetting;
import me.linoxgh.weaponryenhanced.api.settings.EnumSetting;
import me.linoxgh.weaponryenhanced.api.settings.IntegerSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemListSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemStackSetting;
import me.linoxgh.weaponryenhanced.items.ItemList;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemConfig {
    private final WeaponryEnhanced plugin;
    private final File parent;
    private final FileConfiguration cfg;
    private final ItemList itemList;

    public ItemConfig(@NotNull WeaponryEnhanced plugin, @NotNull ItemList itemList) {
        this.plugin = plugin;
        this.parent = plugin.getDataFolder();
        this.cfg = plugin.getConfig();
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
                    if (setting instanceof EnumSetting) {
                        itemCfg.set(key, ((EnumSetting<?>) setting).getDefaultValueName());
                        continue;
                    }
                    itemCfg.set(key, setting.getDefaultValue());
                    continue;
                }

                if (!setting.isApplicable(value)) return;

                if (setting instanceof BooleanSetting) {
                    ((BooleanSetting) setting).setValue((boolean) value);
                } else if (setting instanceof DoubleSetting) {
                    ((DoubleSetting) setting).setValue((double) value);
                } else if (setting instanceof IntegerSetting) {
                    ((IntegerSetting) setting).setValue((int) value);
                } else if (setting instanceof ItemListSetting) {
                    ((ItemListSetting) setting).setValue((List<ItemStack>) value);
                } else if (setting instanceof ItemStackSetting) {
                    ((ItemStackSetting) setting).setValue((ItemStack) value);
                } else if (setting instanceof EnumSetting) {
                    ((EnumSetting<?>) setting).setValue((String) value);
                }
            }
        }

        try {
            cfg.save(parent.getAbsolutePath() + File.separator + "config.yml");
        } catch (IOException e) {
            plugin.getLogger().warning("Could not save default item settings to config.yml");
            e.printStackTrace();
        }
    }

    public void saveItemSettings() {
        ConfigurationSection items = cfg.getConfigurationSection("items");
        if (items == null) items = cfg.createSection("items");

        for (ItemWithHandler<? extends ItemHandler> item : itemList.getItems()) {
            String id = item.getId();
            ConfigurationSection itemCfg = items.getConfigurationSection(id);
            if (itemCfg == null) itemCfg = items.createSection(id);

            for (ItemSetting<?> setting : item.getSettings()) {
                String key = setting.getKey();
                if (setting instanceof EnumSetting) {
                    itemCfg.set(key, ((EnumSetting<?>) setting).getValueName());
                    continue;
                }
                itemCfg.set(key, setting.getValue());
            }
        }

        try {
            cfg.save(parent.getAbsolutePath() + File.separator + "config.yml");
        } catch (IOException e) {
            plugin.getLogger().warning("Could not save item settings to config.yml");
            e.printStackTrace();
        }
    }
}
