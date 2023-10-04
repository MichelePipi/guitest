package xyz.michelepip.guitest;

import java.util.logging.Logger;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import xyz.michelepip.guitest.cmd.GuiTestCmd;
import xyz.michelepip.guitest.cmd.HelloWorldCmd;
import xyz.michelepip.guitest.cmd.moderation.warn.WarnCommand;
import xyz.michelepip.guitest.events.EventTestHandler;
import xyz.michelepip.guitest.events.filter.ChatFilterEventHandler;
import xyz.michelepip.guitest.events.filter.cmd.RefreshChatFilterCommand;
import xyz.michelepip.guitest.guitest.RyseInventoryTest;

/**
 * Hello world!
 *
 */

public final class GuiTest extends JavaPlugin {

    private static GuiTest instance;
    private static Logger logger;

    private final PluginManager pluginManager = getServer().getPluginManager();
    private final InventoryManager inventoryManager = new InventoryManager(this);

    @Override
    public void onLoad() {
        instance = this;
        logger = getLogger();
    }

    @Override
    public void onEnable() {
        infoLog("Registering commands and events...");
        registerCommands();
        registerEvents();

        infoLog("Loading config...");

        saveDefaultConfig();
        reloadConfig();
        ChatFilterEventHandler.refreshFilter();
        inventoryManager.invoke();
        infoLog("Enabled GuiTest Version " + getDescription().getVersion());
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("helloworld").setExecutor(new HelloWorldCmd());
        getCommand("guitest").setExecutor(new GuiTestCmd());
        getCommand("refreshfilter").setExecutor(new RefreshChatFilterCommand());
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("rysetest").setExecutor(new RyseInventoryTest());
    }

    private void registerEvents() {
        pluginManager.registerEvents(new EventTestHandler(), this);
        pluginManager.registerEvents(new GuiTestCmd(), this);
        pluginManager.registerEvents(new ChatFilterEventHandler(), this);
    }

    public static GuiTest getInstance() {
        return instance;
    }

    public static void warnLog(String msg) {
        logger.warning(msg);
    }

    public static void infoLog(String msg) {
        logger.info(msg);
    }

    public static void errLog(String msg) {
        logger.severe(msg);
    }

}
