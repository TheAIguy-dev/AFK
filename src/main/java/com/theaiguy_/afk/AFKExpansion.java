package com.theaiguy_.afk;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

import static com.theaiguy_.afk.AFK.afks;
import static com.theaiguy_.afk.AFK.plugin;
import static com.theaiguy_.afk.Handlers.Placeholders;

public class AFKExpansion extends PlaceholderExpansion
{
    @Override
    public @NotNull String getIdentifier()
    {
        return "afk";
    }

    @Override
    public @NotNull String getAuthor()
    {
        return "TheAIguy_";
    }

    @Override
    public @NotNull String getVersion()
    {
        return "1.2";
    }

    @Override
    public boolean persist()
    {
        return true;
    }
    
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params)
    {
        if (params.equalsIgnoreCase("list")) return afks.toString();
        if (params.equalsIgnoreCase("prefixed") || params.equalsIgnoreCase("suffixed"))
            return Placeholders(player, Handlers.getFormattedString("messages.tab-name-afk"));
        if (params.startsWith("{") && params.endsWith("}"))
            return afks.contains(params.substring(1, params.length() - 1)) ? "true" : "false";
        if (player != null) return afks.contains(player.getName()) ? "true" : "false";


        return null;
    }
}
