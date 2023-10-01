package xyz.michelepip.guitest.cmd.moderation.warn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.michelepip.guitest.api.C;

public class WarnCommand implements CommandExecutor {

    HashMap<Player, Integer> warns = new HashMap<Player, Integer>();
    private final int MAXIMUM_WARNINGS = 3;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(C.RED + "You must be a player to execute this command.");
            return false;
        }

        Player player = (Player) sender;
        Player playerToWarn = Bukkit.getPlayer(args[0]);

        // Assume player exists
        if (warns.containsKey(playerToWarn)) {
            warns.put(playerToWarn, warns.get(playerToWarn) + 1);
        } else {
            warns.put(playerToWarn, 1);
        }

        if (warns.get(playerToWarn) >= MAXIMUM_WARNINGS) {
            playerToWarn.kickPlayer(C.RED
                    + "You have been kicked for having 3 or more warns.\nRefrain from this behavior to prevent further punishment.");
            warns.remove(playerToWarn);
        }

        // Send message to both players
        player.sendMessage(
                C.RED + "You have warned " + playerToWarn.getName() + " for " + warns.get(playerToWarn) + " time(s).");
        playerToWarn.sendMessage(C.RED + "You have been warned by " + player.getName() + " for "
                + warns.get(playerToWarn) + " time(s).");

        return false;
    }
}
