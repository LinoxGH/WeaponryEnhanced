package me.linoxgh.weaponryenhanced.items.weapons;

import me.linoxgh.weaponryenhanced.WeaponryEnhanced;
import me.linoxgh.weaponryenhanced.api.events.FlamethrowerFireEvent;
import me.linoxgh.weaponryenhanced.api.handlers.InteractHandler;
import me.linoxgh.weaponryenhanced.api.recipes.RecipeType;
import me.linoxgh.weaponryenhanced.api.settings.BooleanSetting;
import me.linoxgh.weaponryenhanced.api.settings.DoubleSetting;
import me.linoxgh.weaponryenhanced.api.settings.IntegerSetting;
import me.linoxgh.weaponryenhanced.api.settings.ItemStackSetting;
import me.linoxgh.weaponryenhanced.utils.item.ItemConstructor;
import me.linoxgh.weaponryenhanced.utils.item.ItemWithHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Flamethrower extends ItemWithHandler<InteractHandler> {
    private final BooleanSetting fire = new BooleanSetting("fire-blocks", false);
    private final IntegerSetting amount = new IntegerSetting("particle-amount", 10);
    private final DoubleSetting range = new DoubleSetting("range", 4D);
    private final ItemStackSetting ammo = new ItemStackSetting("ammo", new ItemStack(Material.FIRE_CHARGE));
    private final ItemStack modelItem;

    private final float MAXMINYAW = 25F;
    private final float MAXMINPITCH = 10F;

    public Flamethrower(@NotNull WeaponryEnhanced plugin) {
        super(RecipeType.SHAPED, null, new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.MAGMA_BLOCK), null, new ItemStack(Material.STONE_BUTTON), new ItemStack(Material.IRON_INGOT));
        this.modelItem = new ItemConstructor(
                plugin,
                getId(),
                Material.IRON_HORSE_ARMOR,
                Component.text("Flamethrower").color(NamedTextColor.YELLOW),

                Component.text("[Right Click] ").color(NamedTextColor.GOLD)
                        .append(Component.text("to burst out fire.").color(NamedTextColor.DARK_AQUA)),
                Component.text(" "),
                Component.text("[Range] ").color(NamedTextColor.GRAY)
                        .append(Component.text(range.getValue())).color(NamedTextColor.DARK_AQUA),
                Component.text("[Ammo] ").color(NamedTextColor.GRAY)
                        .append(Component.text("Fire Charge").color(NamedTextColor.DARK_AQUA))
        ).construct();

        addItemSetting(fire, amount, range, ammo);
    }

    @Override
    public @NotNull ItemStack getModelItem() {
        return modelItem;
    }
    @Override
    public @NotNull ItemStack getItem() {
        return modelItem.clone();
    }

    @Override
    public InteractHandler getItemHandler() {
        return (e, p, item) -> {
            if (!e.getAction().isRightClick()) return;

            FlamethrowerFireEvent event = new FlamethrowerFireEvent(p, item, fire.getValue(), amount.getValue(), range.getValue(), ammo.getValue());
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            if (!hasAmmo(p, event.getAmmo())) {
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_STEP, SoundCategory.PLAYERS, 100, 0);
                return;
            }

            Vector direction = p.getEyeLocation().getDirection();
            Location startLoc = p.getEyeLocation().add(direction.clone().multiply(0.5)).subtract(0, 0.5, 0);
            Location endLoc = startLoc.add(direction.clone().multiply(range.getValue()));

            float differenceYaw = (MAXMINYAW * 2F) / event.getAmount();
            for (float offsetYaw = -MAXMINYAW; offsetYaw <= MAXMINYAW; offsetYaw += differenceYaw) {

                float differencePitch = 5F;
                for (float offsetPitch = -MAXMINPITCH; offsetPitch <= MAXMINPITCH; offsetPitch += differencePitch) {
                    startLoc.getWorld().spawnParticle(Particle.SMALL_FLAME, startLoc, 0, endLoc.getX(), endLoc.getY(), endLoc.getZ());
                    startLoc.getWorld().playSound(startLoc, Sound.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 100, 0);

                    RayTraceResult result = startLoc.getBlock().rayTrace(startLoc, direction, event.getRange(), FluidCollisionMode.ALWAYS);
                    if (result == null) continue;
                    Entity entity = result.getHitEntity();
                    if (entity != null) {
                        entity.setFireTicks(150);
                    }
                    if (event.isFire()) {
                        Block hit = result.getHitBlock();
                        if (hit != null) {
                            Block fire = hit.getRelative(BlockFace.UP);
                            if (fire.getType() == Material.AIR) {
                                fire.setType(hit.getType() == Material.SOUL_SAND ? Material.SOUL_FIRE : Material.FIRE);
                            }
                        }
                    }
                    consumeAmmo(p, event.getAmmo());
                }
            }
        };
    }

    private boolean hasAmmo(@NotNull Player p, @NotNull ItemStack ammo) {
        PlayerInventory inv = p.getInventory();
        return inv.contains(ammo);
    }

    private void consumeAmmo(@NotNull Player p, @NotNull ItemStack ammo) {
        PlayerInventory inv = p.getInventory();
        for (ItemStack item : inv.getStorageContents()) {
            if (!item.isSimilar(ammo)) continue;
            int amount = item.getAmount();
            item.setAmount(amount - 1);
            return;
        }
    }

    public @NotNull BooleanSetting getFireSetting() {
        return fire;
    }
    public @NotNull IntegerSetting getAmountSetting() {
        return amount;
    }

    @Override
    public @NotNull String getId() {
        return "flamethrower";
    }
}
