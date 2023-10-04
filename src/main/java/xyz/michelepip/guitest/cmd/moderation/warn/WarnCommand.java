package xyz.michelepip.guitest.cmd.moderation.warn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.michelepip.guitest.api.C;

public class WarnCommand implements CommandExecutor {

    HashMap<OfflinePlayer, Integer> warns = new HashMap<OfflinePlayer, Integer>();
    private final int MAXIMUM_WARNINGS = 3;

    @Override
    @SuppressWarnings("deprecated")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(C.RED + "You must be a player to execute this command.");
            return false;
        }

        // Rewrite the below code but with input validation
        if (args.length == 0) {
            sender.sendMessage(C.RED + "You must specify a player to warn.");
            return false;
        }

        if (args.length > 1) {
            sender.sendMessage(C.RED + "You can only warn one player at a time.");
            return false;
        }

        Player player = (Player) sender;
        OfflinePlayer playerToWarn = Bukkit.getOfflinePlayer(args[0]);

        // Check if player is real
        if (!playerToWarn.hasPlayedBefore()) {
            player.sendMessage(C.RED + "That player has never played before.");
            return false;
        }

        if (warns.containsKey(playerToWarn)) {
            warns.put(playerToWarn, warns.get(playerToWarn) + 1);
        } else {
            warns.put(playerToWarn, 1);
        }

        if (warns.get(playerToWarn) >= MAXIMUM_WARNINGS) {
            if (playerToWarn.isOnline()) {
                playerToWarn.getPlayer().kickPlayer(C.RED
                        + "You have been kicked for having 3 or more warns.\nRefrain from this behavior to prevent further punishment.");
                warns.remove(playerToWarn);
            }
        }

        // Send message to both players
        player.sendMessage(
                C.RED + "You have warned " + playerToWarn.getName() + " for " + warns.get(playerToWarn) + " time(s).");
        if (playerToWarn.isOnline())
            playerToWarn.getPlayer().sendMessage(C.RED + "You have been warned by " + player.getName() + " for "
                    + warns.get(playerToWarn) + " time(s).");

        return false;
    }
}
