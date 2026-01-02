package me.zioles.pickaxe3x3;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PickaxeListener implements Listener {

    private final Pickaxe3x3 plugin;
    private final NamespacedKey key;

    public PickaxeListener(Pickaxe3x3 plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "expire");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.LONG)) return;

        long expire = meta.getPersistentDataContainer().get(key, PersistentDataType.LONG);
        if (System.currentTimeMillis() > expire) {
            e.getPlayer().getInventory().remove(item);
            e.getPlayer().sendMessage("§cPickaxe ini sudah expired!");
            e.setCancelled(true);
            return;
        }

        Block center = e.getBlock();
        World w = center.getWorld();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block b = w.getBlockAt(
                            center.getX() + x,
                            center.getY() + y,
                            center.getZ() + z
                    );
                    if (b.getType() != Material.BEDROCK) {
                        b.breakNaturally(item);
                    }
                }
            }
        }
    }
  }
