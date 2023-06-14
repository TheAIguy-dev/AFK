package com.theaiguy_.afk;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.theaiguy_.afk.AFK.afks;

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
        return "1.0";
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
        {
            if (player != null) return Handlers.Placeholders(player, Handlers.getFormattedString("messages.tab-name-afk"));
            else return Handlers.getFormattedString("messages.tab-name-afk");
        }
        if (player != null) return afks.contains(player.getName()) ? player.getName() : null;
        return afks.contains(params) ? params : null;
    }
}
