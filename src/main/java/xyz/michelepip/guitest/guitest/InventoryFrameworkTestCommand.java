package xyz.michelepip.guitest.guitest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;

public class InventoryFrameworkTestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        final Player p = (Player) sender;
        ChestGui gui = new ChestGui(5, "Test GUI");
        gui.show(p);

        return false;
    }
}
