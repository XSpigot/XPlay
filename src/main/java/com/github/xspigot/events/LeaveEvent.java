package com.github.xspigot.events;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    private final XPlay plugin = XPlay.plugin;

    public void onJoin(PlayerQuitEvent event) {

        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {

            event.setQuitMessage(Utils.getMessageFromConfigWithPlaceholders("hub.leave", event.getPlayer()));

        } else {

            event.setQuitMessage(Utils.getMessageFromConfig("hub.leave"));

        }

    }

}
