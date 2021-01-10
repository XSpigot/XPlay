package com.github.xspigot.commands.alt;

import com.github.xspigot.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAdminVault implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("xplay.main")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }

        //Gives Vault Information
        sender.sendMessage(ChatColor.DARK_RED + "PLUGIN VAULT API " + ChatColor.WHITE + "3.7");
        sender.sendMessage(ChatColor.DARK_RED + "VAULT VERSION " + ChatColor.WHITE + Bukkit.getServer().getPluginManager().getPlugin("Vault").getDescription().getVersion());

        return true;
    }
}
