package xyz.michelepip.guitest.events.filter.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.michelepip.guitest.api.C;
import xyz.michelepip.guitest.events.filter.ChatFilterEventHandler;

public class RefreshChatFilterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(C.RED + "You must be a player to execute this command.");
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("guitest.refreshchatfilter")) {
            p.sendMessage(C.RED
                    + "You do not have permission to execute this command. If you believe this is an error contact an administrator.");
            return true;
        }

        p.sendMessage(C.GREEN + "Refreshing chat filter...");
        ChatFilterEventHandler.refreshFilter();

        return false;
    }
}
