package com.github.xspigot;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Utils {

    private static final FileConfiguration config = XPlay.plugin.getConfig();

    public static String getMessageFromConfig(String location) {
        String value = config.getString(location);
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public static String getMessageFromConfigWithBuiltInPlaceholders(String location, Player player) {
        String value = config.getString(location);
        value.replaceAll("%player%", String.valueOf(player));
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public static Boolean getValueFromConfig(String location) {
        Boolean value = config.getBoolean(location);
        return value;
    }
    public static String getMessageFromConfigWithPlaceholders(String location, Player player) {
        String value = config.getString(location);
        value.replaceAll("%player%", String.valueOf(player));
        value = PlaceholderAPI.setPlaceholders(player, value);
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
