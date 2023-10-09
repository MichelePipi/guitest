package xyz.michelepip.guitest.guitest;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.michelepip.guitest.api.C;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryFrameworkTestCommand implements CommandExecutor {

    private void makeItemGlow(ItemMeta meta) {
    }

    private GuiItem createGuiItem(@NotNull final Component name, @Nullable final List<Component> lore,
                                  @NotNull final Material material, final @NotNull Consumer<InventoryClickEvent> event) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();

        meta.displayName(name);

        if (lore != null)
            meta.lore(lore);
        item.setItemMeta(meta);
        return new GuiItem(item, event);
    }

    private ArrayList<Component> generateLore(final String... strings) {
        ArrayList<Component> lore = new ArrayList<>();
        for (String string :
                strings) {
            lore.add(Component.text(string));
        }
        return lore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        ChestGui gui = new ChestGui(5, "Test GUI");
        StaticPane pane = new StaticPane(0, 0, 9, 5);
        final GuiItem oneDay = createGuiItem(Component.text(C.RED + "One Day"), generateLore(C.GRAY + "Remove player for one day."), Material.RED_TERRACOTTA, event -> {
            event.setCancelled(true);
            p.sendMessage("Holy [one day]");
        });
        pane.addItem(oneDay, 1, 2);
        final GuiItem oneWeek = createGuiItem(Component.text(C.RED + "One Week"), generateLore(C.GRAY + "Remove player for one week."), Material.RED_TERRACOTTA, event -> {
            event.setCancelled(true);
            p.sendMessage("Holy [one week]");
        });
        pane.addItem(oneWeek, 3, 3);
        final GuiItem oneMonth = createGuiItem(Component.text(C.RED + "One Month"), generateLore(C.GRAY + "Remove player for one month."), Material.RED_TERRACOTTA, event -> {
            event.setCancelled(true);
            p.sendMessage("Holy [one month]");
        });
        pane.addItem(oneMonth, 6, 2);
        final GuiItem permanent = createGuiItem(Component.text(C.RED + "Permanent"), generateLore(C.GRAY + "Remove player forever."), Material.RED_TERRACOTTA, event -> {
            event.setCancelled(true);
            p.sendMessage("Holy [permanent]");
        });
        pane.addItem(permanent, 8, 3);
        gui.addPane(pane);

        gui.show(p);

        return false;
    }
}
