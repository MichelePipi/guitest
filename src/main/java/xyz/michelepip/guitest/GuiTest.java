package xyz.michelepip.guitest;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.michelepip.guitest.cmd.GuiTestCmd;
import xyz.michelepip.guitest.cmd.HelloWorldCmd;
import xyz.michelepip.guitest.events.EventTestHandler;
import xyz.michelepip.guitest.events.filter.ChatFilterEventHandler;

/**
 * Hello world!
 *
 */

public class GuiTest extends JavaPlugin {

    private static GuiTest instance;

    private final PluginManager pluginManager = getServer().getPluginManager();

    private void registerCommands() {
        getCommand("helloworld").setExecutor(new HelloWorldCmd());
        getCommand("guitest").setExecutor(new GuiTestCmd());
    }

    private void registerEvents() {
        pluginManager.registerEvents(new EventTestHandler(), this);
        pluginManager.registerEvents(new GuiTestCmd(), this);
        pluginManager.registerEvents(new ChatFilterEventHandler(), this);
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    public static GuiTest getInstance() {
        return instance;
    }
}
