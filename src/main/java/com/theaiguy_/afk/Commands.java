package com.theaiguy_.afk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.theaiguy_.afk.Handlers.resolve;

public class Commands implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (args.length != 0 || !(sender instanceof Player)) return false;
        if (!sender.hasPermission("afk.use")) return false;

        resolve((Player) sender);

        return true;
    }
}
