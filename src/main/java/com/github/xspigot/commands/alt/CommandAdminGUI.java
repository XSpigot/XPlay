package com.github.xspigot.commands.alt;

import com.github.xspigot.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdminGUI implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("xplay.main")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }

        //Opens GUI If Executor Is Player
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
            return true;
        }
        Player player = (Player) sender;
        //Open GUI (WIP)
        player.sendMessage(ChatColor.DARK_RED + "This Feature Is Not Yet Ready");

        return true;
    }
}
