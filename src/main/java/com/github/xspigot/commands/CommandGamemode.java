package com.github.xspigot.commands;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandGamemode implements CommandExecutor, TabCompleter {

    private final List<String> arguments = Arrays.asList("reload", "help", "dev", "vault", "gui");
    private final XPlay plugin = XPlay.plugin;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("xplay.gamemode")) {
            //Give Player No Permission Error
            sender.sendMessage(Utils.getMessageFromConfig("errors.permission"));
            return true;
        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("c")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Creative Mode
                Utils.getMessageFromConfig("admin.gamemodecreative");
                player.setGameMode(GameMode.CREATIVE);

            }

            if (args[0].equalsIgnoreCase("1")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Creative Mode
                Utils.getMessageFromConfig("admin.gamemodecreative");
                player.setGameMode(GameMode.CREATIVE);

            }

            if (args[0].equalsIgnoreCase("creative")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Creative Mode
                Utils.getMessageFromConfig("admin.gamemodecreative");
                player.setGameMode(GameMode.CREATIVE);

            }

            if (args[0].equalsIgnoreCase("a")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Adventure Mode
                Utils.getMessageFromConfig("admin.gamemodeadventure");
                player.setGameMode(GameMode.ADVENTURE);

            }

            if (args[0].equalsIgnoreCase("2")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Adventure Mode
                Utils.getMessageFromConfig("admin.gamemodeadventure");
                player.setGameMode(GameMode.ADVENTURE);

            }

            if (args[0].equalsIgnoreCase("adventure")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Adventure Mode
                Utils.getMessageFromConfig("admin.gamemodeadventure");
                player.setGameMode(GameMode.ADVENTURE);

            }

            if (args[0].equalsIgnoreCase("s")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Survival Mode
                Utils.getMessageFromConfig("admin.gamemodesurvival");
                player.setGameMode(GameMode.SURVIVAL);

            }

            if (args[0].equalsIgnoreCase("0")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Survival Mode
                Utils.getMessageFromConfig("admin.gamemodesurvival");
                player.setGameMode(GameMode.SURVIVAL);

            }

            if (args[0].equalsIgnoreCase("survival")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Survival Mode
                Utils.getMessageFromConfig("admin.gamemodesurvival");
                player.setGameMode(GameMode.SURVIVAL);

            }

            if (args[0].equalsIgnoreCase("sp")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Spectator Mode
                Utils.getMessageFromConfig("admin.gamemodespectator");
                player.setGameMode(GameMode.SPECTATOR);

            }

            if (args[0].equalsIgnoreCase("spectator")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Spectator Mode
                Utils.getMessageFromConfig("admin.gamemodespectator");
                player.setGameMode(GameMode.SPECTATOR);

            }

            if (args[0].equalsIgnoreCase("3")) {
                //Checks If Player Executed Command
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                    return true;
                }

                Player player = (Player) sender;
                //Put Player In Spectator Mode
                Utils.getMessageFromConfig("admin.gamemodespectator");
                player.setGameMode(GameMode.SPECTATOR);

            }

            if (args[0].equalsIgnoreCase("?")) {

                //Gives Usage
                sender.sendMessage(ChatColor.DARK_PURPLE + "Usage: " + ChatColor.AQUA + "/gm " + ChatColor.DARK_RED + "{c, 0, creative, s, 1, survival, a, 2, adventure, sp, 3, spectator}" + ChatColor.BLUE + "{player}");

            }

        }

        if (args.length == 0) {
            //Checks If Player Executed Command
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.getMessageFromConfig("errors.consolerestrict"));
                return true;
            }

            Player player = (Player) sender;
            sender.sendMessage(ChatColor.DARK_RED + "Currently In Gamemode: " + player.getGameMode());

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            arguments.forEach((argument) -> {
                if (argument.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(argument);
            });
            Collections.sort(result);
            return result;
        }
        return null;
    }

}
