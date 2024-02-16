package com.theaiguy_.afk;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.theaiguy_.afk.Handlers.*;

public final class AFK extends JavaPlugin {
    public static List<String> afks = new ArrayList<>();
    public static JavaPlugin plugin;
    public static AFKExpansion expansion;


    @Override
    public void onEnable()
    {
        plugin = this;
        PluginCommand afkCommand = getCommand("afk");
        afkCommand.setExecutor(new Commands());
        afkCommand.setUsage(Placeholders(null, getFormattedString("messages.usage")));
        afkCommand.setPermission(Placeholders(null, getFormattedString("messages.no-permission")));

        Bukkit.getPluginManager().registerEvents(new Events(), plugin);

        saveDefaultConfig();
        File afkFile = new File(getDataFolder(), "afk-players.yml");
        FileConfiguration afk = YamlConfiguration.loadConfiguration(afkFile);
        if (!afkFile.exists())
        {
            saveResource("afk-players.yml", false);
            afk.options().copyDefaults();
            try
            {
                afk.save(afkFile);
            } catch (IOException ignored) {}
        }

        afks = afk.getStringList("afk-players");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
        {
            expansion = new AFKExpansion();
            expansion.register();
        }
    }


    @Override
    public void onDisable() {
        afks.clear();
        saveAfks();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
        {
            expansion.unregister();
        }
    }
}
