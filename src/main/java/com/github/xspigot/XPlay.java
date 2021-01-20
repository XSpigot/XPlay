package com.github.xspigot;

import com.github.xspigot.commands.CommandAdmin;
import com.github.xspigot.commands.CommandLobby;
import com.github.xspigot.commands.CommandSetLobby;
import com.github.xspigot.commands.alt.*;
import com.github.xspigot.events.BasicJoinEvent;
import com.github.xspigot.events.PAPIJoinEvent;
import net.milkbowl.vault.economy.Economy;
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
    public Economy eco;

    public boolean developer = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
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
            getLogger().log(Level.SEVERE, "No Economy Plugin Found");
            getLogger().log(Level.INFO, "If This Error Persists, Check If Vault Is Correctly Installed");
            boolean eco = false;
        }

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

        } else {

            this.getServer().getPluginManager().registerEvents(new PAPIJoinEvent(), this);
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
        RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economy != null) {
            eco = economy.getProvider();
            boolean eco = true;
        }
        return (eco != null);
    }
}
