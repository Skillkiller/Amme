package listeners;

import core.CoreCommands;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 27.09.2017 12:02
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class privateMessage extends ListenerAdapter{

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        System.out.println(CoreCommands.getCurrentSystemTime() + " [PM] [" + event.getAuthor().getName() + "] " + event.getMessage().getContent());


    }
}
