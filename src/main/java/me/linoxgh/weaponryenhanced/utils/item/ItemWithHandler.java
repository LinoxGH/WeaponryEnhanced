package me.linoxgh.weaponryenhanced.utils.item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.linoxgh.weaponryenhanced.api.handlers.ItemHandler;
import me.linoxgh.weaponryenhanced.api.recipes.RecipeType;
import me.linoxgh.weaponryenhanced.api.settings.BooleanSetting;
import me.linoxgh.weaponryenhanced.api.settings.EnumSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemListSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemSetting;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ItemWithHandler<T extends ItemHandler> {
    private final BooleanSetting recipeEnabled = new BooleanSetting("recipe-enabled", true);
    private final EnumSetting<RecipeType> recipeType;
    private final ItemListSetting recipe;

    private final Set<T> handlers = new HashSet<>();
    private final Set<ItemSetting<?>> settings = new HashSet<>();

    public ItemWithHandler(@NotNull RecipeType defaultRecipeType, @Nullable ItemStack... defaultRecipe) {
        recipeType = new EnumSetting<>(RecipeType.class, "recipe-type", defaultRecipeType);
        recipe = new ItemListSetting("recipe", Arrays.asList(defaultRecipe));

        handlers.add(getItemHandler());
        addItemSetting(recipeEnabled, recipeType, recipe);
    }

    public abstract T getItemHandler();
    public void addItemHandler(@NotNull T handler) {
        handlers.add(handler);
    }
    public @NotNull Set<T> getHandlers() {
        return handlers;
    }

    public void addItemSetting(@NotNull ItemSetting<?>... setting) {
        settings.addAll(Arrays.asList(setting));
    }
    public @NotNull Set<ItemSetting<?>> getSettings() {
        return settings;
    }

    public boolean isRecipeEnabled() {
        return recipeEnabled.getValue();
    }
    public @NotNull RecipeType getRecipeType() {
        return RecipeType.valueOf(recipeType.getValueName());
    }
    public @Nullable List<ItemStack> getRecipe() {
        return isRecipeEnabled() ? recipe.getValue() : null;
    }

    public @NotNull BooleanSetting getRecipeEnabledSetting() {
        return recipeEnabled;
    }
    public EnumSetting<RecipeType> getRecipeTypeSetting() {
        return recipeType;
    }
    public @NotNull ItemListSetting getRecipeSetting() {
        return recipe;
    }

    public abstract @NotNull ItemStack getModelItem();
    public abstract @NotNull ItemStack getItem();
    public abstract @NotNull String getId();
}
