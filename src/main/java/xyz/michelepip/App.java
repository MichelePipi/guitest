package xyz.michelepip;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

/**
 * Hello world!
 *
 */
@Plugin(name="guitest", version="1.0-SNAPSHOT")
public class App extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        this.getLogger().info("We're on");
    }

    @Override
    public void onDisable()
    {

    }
}
