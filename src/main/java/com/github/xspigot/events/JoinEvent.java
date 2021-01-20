package com.github.xspigot.events;

import com.github.xspigot.Utils;
import com.github.xspigot.XPlay;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private final XPlay plugin = XPlay.plugin;

    public void onJoin(PlayerJoinEvent event) {

        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {

            event.setJoinMessage(Utils.getMessageFromConfigWithPlaceholders("hub.join", event.getPlayer()));

        } else {

            event.setJoinMessage(Utils.getMessageFromConfig("hub.join"));

        }

    }

}
