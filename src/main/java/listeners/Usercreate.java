package listeners;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.LVL;
import util.SQL;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 21:02
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class Usercreate extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!LVL.ifUserExists(event.getAuthor())) {
            LVL.createUser(event.getAuthor());
        }


    }
}
