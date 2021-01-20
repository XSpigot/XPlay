package com.github.xspigot.events;

import com.github.xspigot.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PAPIJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        event.setJoinMessage(Utils.getMessageFromConfigWithPlaceholders("hub.join", event.getPlayer()));
    }

}