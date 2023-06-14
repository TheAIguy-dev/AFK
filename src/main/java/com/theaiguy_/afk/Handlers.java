package com.theaiguy_.afk;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static com.theaiguy_.afk.AFK.*;

public class Handlers
{
    public static void resolve(Player player)
    {
        if (afks.contains(player.getName()))
        {
            depart_afk(player);
        }
        else
        {
            become_afk(player);
        }
    }

    public static void become_afk(Player player)
    {
        if (!getBoolean("kick-afk"))
        {
            if (!afks.contains(player.getName()))
            {
                afks.add(player.getName());
                saveAfks();

                player.playerListName(Component.text(Placeholders(player, getFormattedString("messages.tab-name-afk"))));

                if (getBoolean("messages.send-afk-message"))
                    player.sendMessage(Component.text(Placeholders(player, getFormattedString("messages.afk"))));
                if (getBoolean("messages.broadcast"))
                {
                    for (Player p : Bukkit.getOnlinePlayers())
                    {
                        if (!p.getName().equals(player.getName()))
                        {
                            p.sendMessage(Component.text(Placeholders(player, getFormattedString("messages.broadcast-on"))));
                        }
                    }
                }
            }
        }
        else player.kick(Component.text(Placeholders(player, getFormattedString("messages.kick"))));
    }

    public static void depart_afk(Player player)
    {
        if (afks.contains(player.getName()))
        {
            afks.remove(player.getName());
            saveAfks();

            if (getBoolean("messages.change-tab"))
                player.playerListName(Component.text(Placeholders(player, getFormattedString("messages.tab-name"))));

            if (getBoolean("messages.send-afk-message"))
                player.sendMessage(Component.text(Placeholders(player, getFormattedString("messages.not-afk"))));
            if (getBoolean("messages.broadcast"))
            {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    if (!p.getName().equals(player.getName()))
                    {
                        p.sendMessage(Component.text(Placeholders(player, getFormattedString("messages.broadcast-on"))));
                    }
                }
            }
        }
    }



    public static boolean getBoolean(String path)
    {
        return plugin.getConfig().getBoolean(path);
    }

    public static int getInt(String path)
    {
        return plugin.getConfig().getInt(path);
    }

    public static String getFormattedString(String path)
    {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(path));
    }

    public static String Placeholders(Player player, String input)
    {
        String name = "None";
        if (player != null) name = player.getName();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, input.replaceAll("%player%", name)));
        else return input.replaceAll("%player%", name);
    }

    public static void saveAfks()
    {
        File afkFile = new File(plugin.getDataFolder(), "afk-players.yml");
        FileConfiguration afk = YamlConfiguration.loadConfiguration(afkFile);
        afk.set("afk-players", afks);
        try
        {
            afk.save(afkFile);
        } catch (IOException ignored) {}
    }
}
