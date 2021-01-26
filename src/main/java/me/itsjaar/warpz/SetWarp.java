package me.itsjaar.warpz;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    private Main plugin;

    public SetWarp(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("setwarp").setExecutor(this);
    }



    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This is a player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("warpz.setwarp")){
            p.sendMessage(Color("&4You don't have the permissions for this command!"));
            return false;
        }
        if (strings.length == 0){
            p.sendMessage(Color("&4A warp name is required!"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) != null){
            p.sendMessage(Color("&4That warp name already exists!"));
            return false;
        }
        Location loc = p.getLocation();
        plugin.getConfig().set(name + ".World", loc.getWorld().getName());
        plugin.getConfig().set(name + ".x", loc.getX());
        plugin.getConfig().set(name + ".y", loc.getY());
        plugin.getConfig().set(name + ".z", loc.getZ());
        plugin.getConfig().set(name + ".Pitch", loc.getPitch());
        plugin.getConfig().set(name + ".Yaw", loc.getYaw());
        plugin.saveConfig();
        p.sendMessage(Color("&aWarp set!"));

        return true;
    }
    private String Color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
