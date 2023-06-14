package com.theaiguy_.afk;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.HashMap;

import static com.theaiguy_.afk.AFK.plugin;
import static com.theaiguy_.afk.AFK.afks;
import static com.theaiguy_.afk.Handlers.*;

public class Events implements Listener
{
    private static final HashMap<String, Integer> tasks = new HashMap<>();
    private static final HashMap<String, Integer> timeouts = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        scheduleTask(event.getPlayer());
        depart_afk(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        depart_afk(event.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        scheduleTask(event.getPlayer());
        if (check(event.getPlayer(), "move")) depart_afk(event.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncChatEvent event)
    {
        scheduleTask(event.getPlayer());
        if (check(event.getPlayer(), "chat")) depart_afk(event.getPlayer());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        scheduleTask(event.getPlayer());
        if (check(event.getPlayer(), "command") && !(event.getMessage().startsWith("/afk ") || event.getMessage().equals("/afk"))) depart_afk(event.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        scheduleTask(event.getPlayer());
        if (check(event.getPlayer(), "interact")) depart_afk(event.getPlayer());
    }


    private static void scheduleTask(Player player)
    {
        if (!timeouts.containsKey(player.getName()))
        {
            timeouts.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> timeouts.remove(player.getName()), 1));
            if (!tasks.containsKey(player.getName()))
                tasks.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Handlers.become_afk(player), getInt("timeout") * 20L));
            else
            {
                Bukkit.getScheduler().cancelTask(tasks.get(player.getName()));
                tasks.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Handlers.become_afk(player), getInt("timeout") * 20L));
            }
        }
    }

    private static boolean check(Player player, String trigger)
    {
        return afks.contains(player.getName()) && getBoolean("triggers." + trigger);
    }
}
