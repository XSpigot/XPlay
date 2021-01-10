package com.github.xspigot.dependencies;


import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlaceholderAPISetup implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            sender.sendMessage(ChatColor.DARK_RED + "VERSION " + ChatColor.WHITE + PlaceholderAPIPlugin.getServerVersion());
            sender.sendMessage(ChatColor.DARK_RED + "PLUGIN API VERSION" + ChatColor.WHITE + "2.10.9");
        }

        return true;
    }
}
