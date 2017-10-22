package listeners;

import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATICS;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 04.10.2017 11:55
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class reconnectlistener extends ListenerAdapter{
    @Override
    public void onReconnect(ReconnectedEvent event) {

        System.out.println("[INFO] RECONNECT");

        STATICS.reconnectCount++;

    }
}
