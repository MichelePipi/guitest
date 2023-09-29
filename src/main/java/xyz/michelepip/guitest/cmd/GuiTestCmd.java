package xyz.michelepip.guitest.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static xyz.michelepip.guitest.api.Consts.ONE_SECOND_AS_TICKS;;

public class GuiTestCmd implements CommandExecutor, Listener {

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

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {
        Player clicker = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Test GUI")) {
            e.setCancelled(true);
            clicker.closeInventory();
            clicker.sendMessage("You clicked the test item!");

            // Update player inv to prevent ghost item
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("GuiTest"),
                    () -> clicker.updateInventory(), ONE_SECOND_AS_TICKS);
        } else
            clicker.sendMessage("You clicked something else!");
    }

}
