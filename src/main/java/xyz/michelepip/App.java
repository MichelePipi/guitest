package xyz.michelepip;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

import xyz.michelepip.cmd.HelloWorldCmd;

/**
 * Hello world!
 *
 */
@Plugin(name = "guitest", version = "1.0-SNAPSHOT")
@Commands(@Command(name = "helloworld", permission = "guitest.helloworld", permissionMessage = "You do not have the required permissions to execute this command."))
public class App extends JavaPlugin {
    private void registerCommands() {
        getCommand("helloworld").setExecutor(new HelloWorldCmd());
    }

    @Override
    public void onEnable() {
        registerCommands();
    }

    @Override
    public void onDisable() {

    }
}
