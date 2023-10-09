package xyz.michelepip.guitest.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventTestHandler implements Listener {

    @EventHandler
    public void playerJoinEventHandler(PlayerJoinEvent event)
    {
        Player joiner = event.getPlayer();
        final TextComponent textComponent = Component.text("[", NamedTextColor.GRAY)
                .append(Component.text("+", NamedTextColor.GREEN))
                .append(Component.text("]", NamedTextColor.GRAY))
                .append(Component.text(" " + joiner.getName()));
        event.joinMessage(textComponent);
    }

}
