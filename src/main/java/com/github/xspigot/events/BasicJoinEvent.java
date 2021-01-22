package com.github.xspigot.events;

import com.github.xspigot.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicJoinEvent implements Listener {

    public boolean jointp = Utils.getValueFromConfig("hub.teleportonjoin");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (jointp) {

            player.teleport(Bukkit.getWorld("world").getSpawnLocation());

        }

        event.setJoinMessage(Utils.getMessageFromConfigWithBuiltInPlaceholders("hub.join", event.getPlayer()));
    }

}
