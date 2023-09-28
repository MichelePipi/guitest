package xyz.michelepip.guitest;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.michelepip.guitest.cmd.GuiTestCmd;
import xyz.michelepip.guitest.cmd.HelloWorldCmd;
import xyz.michelepip.guitest.events.EventTestHandler;

/**
 * Hello world!
 *
 */

public class App extends JavaPlugin {
    private void registerCommands() {
        getCommand("helloworld").setExecutor(new HelloWorldCmd());
        getCommand("guitest").setExecutor(new GuiTestCmd());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new EventTestHandler(), this);
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {

    }
}
