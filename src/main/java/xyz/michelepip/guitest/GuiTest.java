package xyz.michelepip.guitest;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.michelepip.guitest.cmd.HelloWorldCmd;
import xyz.michelepip.guitest.cmd.moderation.warn.WarnCommand;
import xyz.michelepip.guitest.events.EventTestHandler;
import xyz.michelepip.guitest.events.filter.ChatFilterEventHandler;
import xyz.michelepip.guitest.events.filter.cmd.RefreshChatFilterCommand;
import xyz.michelepip.guitest.guitest.InventoryFrameworkTestCommand;

/**
 * Hello world!
 *
 */

public final class GuiTest extends JavaPlugin {

    private static GuiTest instance;
    private static Logger logger;

    private final PluginManager pluginManager = getServer().getPluginManager();

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
        infoLog("Enabled GuiTest Version " + getDescription().getVersion());
    }

    private void registerCommands() {
        getCommand("helloworld").setExecutor(new HelloWorldCmd());
        getCommand("refreshfilter").setExecutor(new RefreshChatFilterCommand());
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("iftest").setExecutor(new InventoryFrameworkTestCommand());
    }

    private void registerEvents() {
        pluginManager.registerEvents(new EventTestHandler(), this);
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
