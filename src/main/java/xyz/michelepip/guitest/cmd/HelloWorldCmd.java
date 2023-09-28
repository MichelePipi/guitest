package xyz.michelepip.guitest.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HelloWorldCmd implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,
            final String label, final String[] args) {
        final Player player = (Player) sender;
        player.sendMessage("Hello, World.");
        return true;
    }
}
