package com.github.xspigot.events;

import com.github.xspigot.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BasicQuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        event.setQuitMessage(Utils.getMessageFromConfigWithBuiltInPlaceholders("hub.leave", event.getPlayer()));
    }

}
