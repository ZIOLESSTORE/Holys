package me.zioles.pickaxe3x3;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class ToolsCommand implements CommandExecutor {

    private final Pickaxe3x3 plugin;
    private final NamespacedKey key;

    public ToolsCommand(Pickaxe3x3 plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "expire");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 3) return false;
        if (!args[0].equalsIgnoreCase("give")) return false;

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) return true;

        if (!args[2].equalsIgnoreCase("pickaxe3x3")) return true;

        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = pickaxe.getItemMeta();

        meta.setDisplayName("§bPickaxe 3x3");
        meta.setLore(Arrays.asList(
                "§7Mining 3x3",
                "§eExpired dalam 20 hari"
        ));

        long expireTime = System.currentTimeMillis() + (20L * 24 * 60 * 60 * 1000);
        meta.getPersistentDataContainer().set(key, PersistentDataType.LONG, expireTime);

        pickaxe.setItemMeta(meta);
        target.getInventory().addItem(pickaxe);

        return true;
    }
            }
