package me.linoxgh.weaponryenhanced;

import com.destroystokyo.paper.MaterialTags;
import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.items.ItemList;
import me.linoxgh.weaponryenhanced.items.weapons.Flamethrower;
import me.linoxgh.weaponryenhanced.utils.config.ItemConfig;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class WeaponryEnhanced extends JavaPlugin {
    private ItemList itemList;
    private ItemConfig itemConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        itemList = new ItemList();
        setupItems();

        itemConfig = new ItemConfig(getConfig(), itemList);
        itemConfig.loadItemSettings();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupItems() {
        itemList.addItem(new Flamethrower());
    }

    private void setupRecipes() {
        for (ItemWithHandler<? extends ItemHandler> item : itemList.getItems()) {
            if (item.isRecipeEnabled()) {
                Recipe recipe;
                switch (item.getRecipeType()) {
                    case SHAPED:
                        if (item.getRecipe() == null || item.getRecipe().size() < 9) continue;
                        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(this, item.getId()), item.getModelItem());
                        shapedRecipe.setIngredient('a', item.getRecipe().get(0));
                        shapedRecipe.setIngredient('b', item.getRecipe().get(1));
                        shapedRecipe.setIngredient('c', item.getRecipe().get(2));

                        shapedRecipe.setIngredient('d', item.getRecipe().get(3));
                        shapedRecipe.setIngredient('e', item.getRecipe().get(4));
                        shapedRecipe.setIngredient('f', item.getRecipe().get(5));

                        shapedRecipe.setIngredient('g', item.getRecipe().get(6));
                        shapedRecipe.setIngredient('h', item.getRecipe().get(7));
                        shapedRecipe.setIngredient('i', item.getRecipe().get(8));

                        shapedRecipe.shape("abc", "def", "ghi");
                        recipe = shapedRecipe;
                        break;

                    case SHAPELESS:
                        if (item.getRecipe() == null || item.getRecipe().isEmpty()) continue;
                        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this, item.getId()), item.getModelItem());
                        for (ItemStack ingredient : item.getRecipe()) shapelessRecipe.addIngredient(ingredient);
                        recipe = shapelessRecipe;
                        break;

                    case FURNACE:
                        if (item.getRecipe() == null || item.getRecipe().isEmpty()) continue;
                        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(new NamespacedKey(this, item.getId()), item.getModelItem(), new RecipeChoice.ExactChoice(item.getRecipe()), 0f, 1600);
                        recipe = furnaceRecipe;
                        break;

                    case NONE:
                    default:
                        continue;
                }
                Bukkit.addRecipe(recipe);
            }
        }
    }
}
