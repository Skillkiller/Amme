package listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.SQL;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 27.09.2017 12:07
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class overflowListener extends ListenerAdapter{
    public void onGuildJoin(GuildJoinEvent event) {
        Guild g = event.getGuild();
            SQL.createServer(g);
            System.out.println("[Amme]System started on: " + g.getName());



    }

}
