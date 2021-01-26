package me.itsjaar.warpz;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {
    private Main plugin;

    public DelWarp(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("delwarp").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This is a player only command!");
            return false;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("warpz.delwarp")) {
            p.sendMessage(Color("&4You don't have the permissions for this command!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(Color("&4A warp name is required!"));
            return false;
        }

        String name = strings[0].toLowerCase();
        if(plugin.getConfig().get(name) == null){
            p.sendMessage("&4No Warp with that name!");
            return false;
        }

        plugin.getConfig().set(name, null);
        plugin.saveConfig();
        p.sendMessage("&aWarp &e" + name + " has been deleted!");
        return true;
    }

    private String Color (String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}