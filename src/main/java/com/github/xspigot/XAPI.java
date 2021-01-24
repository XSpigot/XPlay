package com.github.xspigot;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class XAPI {

    private final XPlay plugin = XPlay.plugin;

    //Plugin Information Section

    //Get Version
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }


    //Plugin Ease Of Use Library API

    //API To Set The Lobby With Coordinates
    public void setLobbyWithCoordinates(Integer posx, Integer posy, Integer posz, Float yaw, World world) {

        world.setSpawnLocation(posx, posy, posz, yaw);

        return;

    }

    //API To Set The Lobby At The Player's Location
    public void setLobbyAtPlayerLocation(Player player, World world) {

        int PosX = (int) player.getLocation().getX();
        int PosY = (int) player.getLocation().getY();
        int PosZ = (int) player.getLocation().getZ();
        int Angle = (int) player.getLocation().getYaw();

        player.getServer().getWorld(String.valueOf(world)).setSpawnLocation(PosX, PosY, PosZ, Angle);

        return;

    }

}
