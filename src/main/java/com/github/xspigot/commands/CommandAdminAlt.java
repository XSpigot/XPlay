package com.github.xspigot.commands;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdminAlt implements CommandExecutor {

    private final XPlay plugin = XPlay.plugin;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("xplay.main")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }
        if (label.equalsIgnoreCase("xdev")) {
            //Enabled Dev Mode
            sender.sendMessage(ChatColor.DARK_RED + "Dev Mode Enabled");
            plugin.developer = true;
        }

        if (label.equalsIgnoreCase("xhelp")) {
            sendHelpMessage(sender);
            return true;
        }

        if (label.equalsIgnoreCase("xvault")) {
            //Gives Vault Information
            sender.sendMessage(ChatColor.DARK_RED + "PLUGIN VAULT API " + ChatColor.WHITE + "3.7");
            sender.sendMessage(ChatColor.DARK_RED + "VAULT VERSION " + ChatColor.WHITE + Bukkit.getServer().getPluginManager().getPlugin("Vault").getDescription().getVersion());
        }

        if (label.equalsIgnoreCase("xgui")) {
            //Opens GUI If Executor Is Player
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                return true;
            }
            Player player = (Player) sender;
            //Open GUI (WIP)
            player.sendMessage(ChatColor.DARK_RED + "This Feature Is Not Yet Ready");
        }

        if (label.equalsIgnoreCase("xreload")) {
            //Reloads Config
            plugin.reloadConfig();
            sender.sendMessage(Utils.getMessageFromConfig("configs.reload"));
        }

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
