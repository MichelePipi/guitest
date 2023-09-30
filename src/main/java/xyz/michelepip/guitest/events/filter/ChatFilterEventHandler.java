package xyz.michelepip.guitest.events.filter;

import java.util.ArrayList;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import xyz.michelepip.guitest.GuiTest;
import xyz.michelepip.guitest.api.C;
import xyz.michelepip.guitest.api.Util;

public class ChatFilterEventHandler implements Listener {

    // Create list from
    // GuiTest.getInstance().getConfig().getStringList("bad_words");
    private static ArrayList<String> badWords;

    public static final void refreshFilter() {
        GuiTest.getInstance().reloadConfig();
        // Create list from
        // GuiTest.getInstance().getConfig().getStringList("bad_words");
        badWords = (ArrayList<String>) GuiTest.getInstance().getConfig()
                .getStringList("bad_words");
    }

    // TODO: Add aggressive mode (check if word anywhere in string)
    // TOOD: normal mode; check if bad word is a word in string, not just in one
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        String message = e.getMessage().toLowerCase();
        final Player p = e.getPlayer();

        for (final String word : message.split(" '")) {
            for (final String badWord : badWords) {
                if (Util.stringExistsInString(word, badWord)) {
                    if (!p.hasPermission("guitest.chatfilter.bypass")) {
                        p.sendMessage(C.RED
                                + "That word is against the server policies. Your message was not sent and this has been logged.\nPlease refrain from this behavior to avoid punishment.");
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_CAT_DEATH, 1.0f, 0.5f);
                        e.setCancelled(true);
                        return;
                    }
                    p.sendMessage(
                            C.RED + "You sent an innapropriate message, but you have permission to bypass the filter.");
                }
            }
        }
    }
}
