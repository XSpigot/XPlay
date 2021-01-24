package com.github.xspigot;

import com.github.xspigot.api.SetLobbyAPI;
import com.github.xspigot.commands.CommandAdmin;
import com.github.xspigot.commands.CommandLobby;
import com.github.xspigot.commands.CommandSetLobby;
import com.github.xspigot.commands.alt.*;
import com.github.xspigot.events.BasicJoinEvent;
import com.github.xspigot.events.BasicQuitEvent;
import com.github.xspigot.events.PAPIJoinEvent;
import com.github.xspigot.events.PAPIQuitEvent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

//CONTRIBUTORS
//EPICGAMER73
//WSMAN217

public final class XPlay extends JavaPlugin implements Listener {

    public static XPlay plugin;
    public SetLobbyAPI lobbyapi;
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    public boolean developer = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        lobbyapi = new SetLobbyAPI();
        plugin = this;

        setUpCommands();
        setUpEvents();

        getLogger().log(Level.INFO, "XPLAY");
        getLogger().log(Level.INFO, "From: " + getDescription().getAuthors());
        getLogger().log(Level.INFO, "Running: " + getDescription().getVersion());
        getLogger().log(Level.INFO, "SpigotMC Release");
        getLogger().log(Level.INFO, "Dependencies: " + getDescription().getDepend());
        getLogger().log(Level.INFO, "Soft Dependencies: " + getDescription().getSoftDepend());
        getLogger().log(Level.INFO, "API Version: " + getDescription().getAPIVersion());

        developer = Utils.getValueFromConfig("tools.dev");

        if (developer) {
            getLogger().log(Level.WARNING, "Running Snapshot [DEV RELEASE]");
            getLogger().log(Level.WARNING, "Developer Releases Are Not Stable - Use At Your Own Risk");
        }

        if (!setupEconomy()) {
            getLogger().log(Level.SEVERE, "Vault Is Not Installed - Install Vault And Restart The Server");
            getLogger().log(Level.INFO, "If This Error Persists, Check If Vault Is Correctly Installed");
            getServer().getPluginManager().disablePlugin(this);
        }

        setupPermissions();
        setupChat();

    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "XPLAY");
        getLogger().log(Level.INFO, "Disabling Plugin");
        getLogger().log(Level.INFO, "Running: " + getDescription().getVersion());
        getLogger().log(Level.INFO, "SpigotMC Release");
        getLogger().log(Level.INFO, "Error? (Coming Soon) bit.ly/xplayerror");
    }

    private void setUpCommands() {
        CommandAdmin adminCommand = new CommandAdmin();
        setUpCommandWithExecutor("xplay", adminCommand, adminCommand);
        setUpCommand("setlobby", new CommandSetLobby());
        setUpCommand("xdev", new CommandAdminDev());
        setUpCommand("xhelp", new CommandAdminHelp());
        setUpCommand("xvault", new CommandAdminVault());
        setUpCommand("xreload", new CommandAdminReload());
        setUpCommand("xgui", new CommandAdminGUI());
        setUpCommand("lobby", new CommandLobby());
    }

    private void setUpEvents() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            this.getServer().getPluginManager().registerEvents(new BasicJoinEvent(), this);
            this.getServer().getPluginManager().registerEvents(new BasicQuitEvent(), this);

        } else {

            this.getServer().getPluginManager().registerEvents(new PAPIJoinEvent(), this);
            this.getServer().getPluginManager().registerEvents(new PAPIQuitEvent(), this);
        }
    }

    private void setUpCommandWithExecutor(String command, CommandExecutor executor, TabCompleter completer) {
        PluginCommand pluginCommand = this.getCommand(command);

        if (pluginCommand == null)
            throw new NullPointerException("Command not found: " + command + ".");
        pluginCommand.setExecutor(executor);
        pluginCommand.setTabCompleter(completer);
    }

    private void setUpCommand(String command, CommandExecutor executor) {
        PluginCommand pluginCommand = this.getCommand(command);

        if (pluginCommand == null)
            throw new NullPointerException("Command not found: " + command + ".");
        pluginCommand.setExecutor(executor);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

}
