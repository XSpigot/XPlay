package com.github.xspigot.commands.alt;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAdminDev implements CommandExecutor {

    private final XPlay plugin = XPlay.plugin;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("xplay.main")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }

        //Enabled Dev Mode
        sender.sendMessage(ChatColor.DARK_RED + "Dev Mode Enabled");
        plugin.developer = true;

        return true;
    }
}
