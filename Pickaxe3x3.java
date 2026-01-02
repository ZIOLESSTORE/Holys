package me.zioles.pickaxe3x3;

import org.bukkit.plugin.java.JavaPlugin;

public class Pickaxe3x3 extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("tools").setExecutor(new ToolsCommand(this));
        getServer().getPluginManager().registerEvents(new PickaxeListener(this), this);
    }
}
