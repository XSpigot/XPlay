package com.github.xspigot.dependencies;


import com.sk89q.worldedit.WorldEdit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldEditSetup implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
            sender.sendMessage(ChatColor.DARK_RED + "VERSION " + ChatColor.WHITE + WorldEdit.getVersion());
            sender.sendMessage(ChatColor.DARK_RED + "PLUGIN API VERSION" + ChatColor.WHITE + "7.2.0");
        }

        return true;
    }
}
