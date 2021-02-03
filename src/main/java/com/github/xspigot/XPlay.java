package com.github.xspigot;

import com.github.xspigot.commands.CommandAdmin;
import com.github.xspigot.commands.CommandGamemode;
import com.github.xspigot.commands.CommandLobby;
import com.github.xspigot.commands.CommandSetLobby;
import com.github.xspigot.commands.alt.*;
import com.github.xspigot.events.JoinEvent;
import com.github.xspigot.events.QuitEvent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

//CONTRIBUTORS
//EPICGAMER73
//WSMAN217

//Int21R4 - Planned Patch Notes:
//
//- Added Custom Vault Economy
//- Added Feature To Teleport The Player To The Lobby On Join
//- Added Placeholder API Support For All Config Values
//- Added API Support
//- Added Grief Protection
//- Added Grief Protection Regions
//- Added 12 New Commands --> /forcejoin, /forceleave, /togglechat, /sun, /rain, /kickall, /banall, /suicide, /protect, /feed, /heal, and /gm
//- /forcejoin Fakes A Player To Join, And Sends A Message To Everyone In The Server That The Player Has Joined
//- /forceleave Does The Same Thing As /forcejoin, But Makes The Player Leave Instead. If The Targeted Player Is Online, Then It Will Kick Them With A Customizable Error Message.
//- /togglechat Allows The Player To Toggle Receiving Messages From Other Players.
//- /sun Sets The Weather To Sun
//- /rain Sets The Weather To Rain/Snow
//- /kickall Kicks Every Player Online, Except The Player Executing The Command
//- /banall Is A More Severe Version Of /kickall, Banning Every Player (Except, Of Course, The Player Executing The Command)
//- /suicide Kills The Player, But Gives Them The Option To Go Back To Their Death Point. If commands.deleteinventory Is Set To True, Their Inventory Is Cleared, With No Way To Get It Back.
//- /protect Sets The World Edit Selection To Not Be Editable Without The Permission xplay.editprotect
//- /feed Sets The Player's Hunger Points To Maximum.
//- /heal Sets All THe Player's Hearts And Hunger Points To Maximum.
//- /gm Is A Alias For /gamemode, and Acts Exactly The Same As The Default Command, But Has Some Extra Options, Such As c Or 1 For Creative M

public final class XPlay extends JavaPlugin implements Listener {

    public static XPlay plugin;
    public XAPI api;
    private static Economy econ = null;
    private static Permission perms = null;

    public boolean developer = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Logger logger = this.getLogger();
        api = new XAPI();
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

        Metrics metrics = new Metrics(this, 87463);

        new UpdateCheck(this, 87463).getVersion(version -> {
            if (!(this.getDescription().getVersion().equalsIgnoreCase(version))) {
                getLogger().log(Level.WARNING, "A New Update For XPlay Is Avaliable!");
                getLogger().log(Level.WARNING, "Make Sure To Download It As Soon As Possible");
                getLogger().log(Level.WARNING, "https://www.spigotmc.org/resources/87463/");
            }
        });

        if (!setupEconomy()) {
            getLogger().log(Level.SEVERE, "Vault Is Not Installed - Install Vault And Restart The Server");
            getLogger().log(Level.INFO, "If This Error Persists, Check If  Vault Is Correctly Installed");
            getServer().getPluginManager().disablePlugin(this);
        }

        setupPermissions();

    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "XPLAY");
        getLogger().log(Level.INFO, "Disabling Plugin");
        getLogger().log(Level.INFO, "Running: " + getDescription().getVersion());
        getLogger().log(Level.INFO, "SpigotMC Release");
    }

    private void setUpCommands() {
        CommandAdmin adminCommand = new CommandAdmin();
        CommandGamemode gamemodeCommand = new CommandGamemode();
        setUpCommandWithExecutor("xplay", adminCommand, adminCommand);
        setUpCommandWithExecutor("gm", gamemodeCommand, gamemodeCommand);
        setUpCommand("setlobby", new CommandSetLobby());
        setUpCommand("xdev", new CommandAdminDev());
        setUpCommand("xhelp", new CommandAdminHelp());
        setUpCommand("xvault", new CommandAdminVault());
        setUpCommand("xreload", new CommandAdminReload());
        setUpCommand("xgui", new CommandAdminGUI());
        setUpCommand("lobby", new CommandLobby());
    }

    private void setUpEvents() {

            this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
            this.getServer().getPluginManager().registerEvents(new QuitEvent(), this);

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


}
