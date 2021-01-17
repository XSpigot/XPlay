package com.github.xspigot;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    private static final FileConfiguration config = XPlay.plugin.getConfig();

    public static String getMessageFromConfig(String location) {
        String value = config.getString(location);
        if (value == null)
            return ChatColor.RED + "Config Message \"" + location + "\" Not Found.";
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public static Boolean getValueFromConfig(String location) {
        Boolean value = config.getBoolean(location);
        return value;
    }

}
