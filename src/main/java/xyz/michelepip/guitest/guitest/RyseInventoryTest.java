package xyz.michelepip.guitest.guitest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import xyz.michelepip.guitest.GuiTest;
import xyz.michelepip.guitest.api.C;

public class RyseInventoryTest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            GuiTest.errLog("Only players may execute this command.");
            return true;
        }

        final Player p = (Player) sender;
        RyseInventory inv = RyseInventory.builder()
                .rows(3)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(final Player opener, final InventoryContents inventoryContents) {
                        opener.sendMessage("Inventory opened!");
                    }
                })
                .build(GuiTest.getInstance());

        inv.open(p, 0);

        return false;
    }

}
