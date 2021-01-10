package com.github.xspigot.commands;

import com.github.xspigot.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLobby implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("lobby")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessageFromConfig("errors.consolerestrict")));
            }

            ((Player) sender).teleport(Bukkit.getWorld("world").getSpawnLocation());
        }

        return true;
    }

}
