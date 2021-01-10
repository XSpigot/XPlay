package com.github.xspigot.commands.alt;

import com.github.xspigot.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAdminHelp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("xplay.main")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }

        sendHelpMessage(sender);
        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_RED + "XDEV " + ChatColor.WHITE + "Developer Tools (/xdev, /xplay dev)");
        sender.sendMessage(ChatColor.DARK_RED + "XGUI " + ChatColor.WHITE + "GUI Menu (/xgui, /xplay gui)");
        sender.sendMessage(ChatColor.DARK_RED + "XVAULT " + ChatColor.WHITE + "Vault Information (/xvault, xplay vault)");
        sender.sendMessage(ChatColor.DARK_RED + "XRELOAD " + ChatColor.WHITE + "Reload Configurations (/xreload, xplay reload)");
        sender.sendMessage(ChatColor.DARK_RED + "XHELP " + ChatColor.WHITE + "Help Page (/xhelp, /xplay help)");
        sender.sendMessage(ChatColor.DARK_RED + "SETLOBBY " + ChatColor.WHITE + "Set Lobby (/setlobby)");
        sender.sendMessage(ChatColor.DARK_RED + "LOBBY " + ChatColor.WHITE + "Teleport To Lobby (/lobby)");
    }

}
