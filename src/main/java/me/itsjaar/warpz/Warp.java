package me.itsjaar.warpz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    private Main plugin;

    public Warp(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This is a player only command!");
            return false;
        }

        Player p = (Player) commandSender;

        if (!p.hasPermission("warpz.warp")){
            p.sendMessage(Color("&4You don't have the permissions for this command!"));
            return false;
        }

        if (strings.length == 0){
            p.sendMessage(Color("&4A warp name is required!"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) == null){
            p.sendMessage(Color("&4No warp with that name!"));
            return false;
        }
        Location loc;
        double x = plugin.getConfig().getDouble(name + ".x");
        double y = plugin.getConfig().getDouble(name + ".y");
        double z = plugin.getConfig().getDouble(name + ".z");
        double yaw = plugin.getConfig().getDouble(name + ".Yaw");
        double pitch = plugin.getConfig().getDouble(name + ".Pitch");
        String world = plugin.getConfig().getString(name + ".World");
        loc = new Location(Bukkit.getWorld(world),x,y,z,(float) yaw, (float) pitch);
        p.teleport(loc);
        p.sendMessage(Color("&aYou have been warped to &e" + name));

        return true;
    }
    private String Color (String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
