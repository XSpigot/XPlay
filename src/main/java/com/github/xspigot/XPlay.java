package com.github.xspigot;

import com.github.xspigot.commands.CommandAdmin;
import com.github.xspigot.commands.CommandAdminAlt;
import com.github.xspigot.commands.CommandHub;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class XPlay extends JavaPlugin implements Listener {
    public static XPlay plugin;
    public Economy eco;

    public boolean developer = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        plugin = this;

        setUpCommands();
        setUpDependencies();

        getLogger().log(Level.INFO, "XPLAY");
        getLogger().log(Level.INFO, "From: " + getDescription().getAuthors());
        getLogger().log(Level.INFO, "Running: " + getDescription().getVersion());
        getLogger().log(Level.INFO, "SpigotMC Release");
        getLogger().log(Level.INFO, "Dependencies: " + getDescription().getDepend());
        getLogger().log(Level.INFO, "API Version: " + getDescription().getAPIVersion());

        developer = getConfig().getBoolean("tools.dev");

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
        setUpCommand("setlobby", new CommandHub());
        setUpCommand("xdev", new CommandAdminAlt());
        setUpCommand("xhelp", new CommandAdminAlt());
        setUpCommand("xvault", new CommandAdminAlt());
        setUpCommand("xreload", new CommandAdminAlt());
        setUpCommand("xgui", new CommandAdminAlt());
        setUpCommand("lobby", new CommandHub());
    }

    private void setUpDependencies() {
        //Check If Vault Is Installed
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            getLogger().log(Level.INFO, "Connected Into Vault");
        } else {
            getLogger().log(Level.SEVERE, "Vault Is Not Installed! Disabling Plugin");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        //Check If Placeholder API Is Installed
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Connected Into Placeholder API");
            Bukkit.getPluginManager().registerEvents(this, this);
        }
        //Check If World Edit Is Installed
        if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
            getLogger().log(Level.INFO, "Connected Into World Edit");
            Bukkit.getPluginManager().registerEvents(this, this);
        }
        //Check If Protocol Lib Is Installed
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            getLogger().log(Level.INFO, "Connected Into Protocol Lib");
            Bukkit.getPluginManager().registerEvents(this, this);
        }
        //Check If Via Version Is Installed
        if (Bukkit.getPluginManager().getPlugin("ViaVersion") != null) {
            getLogger().log(Level.INFO, "Connected Into Via Version");
            Bukkit.getPluginManager().registerEvents(this, this);
        }
        //Check If Plot Squared Is Installed
        if (Bukkit.getPluginManager().getPlugin("PlotSquared") != null) {
            getLogger().log(Level.INFO, "Connected Into Plot Squared");
            Bukkit.getPluginManager().registerEvents(this, this);
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
