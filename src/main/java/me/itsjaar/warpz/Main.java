package me.itsjaar.warpz;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable(){
        getLogger().info("Warpz has been enabled!");
        getCommand("warp").setExecutor(new Warp(this));
        getCommand("setwarp").setExecutor(new SetWarp(this));
        getCommand("delwarp").setExecutor(new DelWarp(this));
    }

    @Override
    public void onDisable(){
        getLogger().info("Warpz has been disabled!");
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }

}
