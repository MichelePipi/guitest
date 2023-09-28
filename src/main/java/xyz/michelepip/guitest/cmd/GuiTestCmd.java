package xyz.michelepip.guitest.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiTestCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be executed by a player.");
            return true;
        }

        Player executor = (Player) sender;
        Inventory inv = Bukkit.createInventory(null, 9, "Test GUI");
        ItemStack testItem = new ItemStack(Material.NETHERITE_SWORD, 1);
        inv.setItem(4, testItem);

        executor.openInventory(inv);

        return true;
    }

}
