package com.github.xspigot.dependencies;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldEditSetup implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
            
        }

        return true;
    }
}
