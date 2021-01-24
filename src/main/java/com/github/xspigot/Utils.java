package com.github.xspigot;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Utils {

    private static final FileConfiguration config = XPlay.plugin.getConfig();

    public static String getMessageFromConfig(String location) {
        String value = config.getString(location);
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix") + value);
    }

    public static String getStringFromConfig(String location) {
        String value = config.getString(location);
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return value;
    }

    public static Boolean getValueFromConfig(String location) {
        Boolean value = config.getBoolean(location);
        return value;
    }

    public static String getMessageFromConfigWithPlaceholders(String location, Player player) {
        String value = config.getString(location);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            value = PlaceholderAPI.setPlaceholders(player, value);
        }
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";

        value.replaceAll("%player%", String.valueOf(player));
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix") + value);
    }

}
