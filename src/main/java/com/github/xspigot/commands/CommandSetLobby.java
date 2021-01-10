package com.github.xspigot.commands;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetLobby implements CommandExecutor {

    private final XPlay plugin = XPlay.plugin;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (!sender.hasPermission("xplay.lobby")) {
                //Give Player No Permission Error
                sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
                return true;
            }
            if (args.length >= 0) {
                if (args.length < 3) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessageFromConfig("errors.consolerestrict")));
                    }
                    Player player = (Player) sender;
                    int PosX = (int) player.getLocation().getX();
                    int PosY = (int) player.getLocation().getY();
                    int PosZ = (int) player.getLocation().getZ();
                    int Angle = (int) player.getLocation().getYaw();

                    player.getServer().getWorld("world").setSpawnLocation(PosX, PosY, PosZ, Angle);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessageFromConfig("hub.lobby")));
                }
            }

            if (args.length == 4) {
                if (IntCheck(args[0])) {
                    if (IntCheck(args[1])) {
                        if (IntCheck(args[2])) {
                            if (IntCheck(args[3])) {
                                Player player = (Player) sender;
                                int InpX = (int) Integer.parseInt(args[0]);
                                int InpY = (int) Integer.parseInt(args[1]);
                                int InpZ = (int) Integer.parseInt(args[2]);
                                int Ang = (int) Integer.parseInt(args[3]);

                                player.getServer().getWorld("world").setSpawnLocation(InpX, InpY, InpZ, Ang);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessageFromConfig("hub.lobby")));
                                return true;
                            }
                        }
                    }
                }
            }
        return true;
        }

    public boolean IntCheck(String str) {

        try {
            Integer.parseInt(str);

        } catch (Throwable e) {
            return false;
        }

        return true;
    }

}
