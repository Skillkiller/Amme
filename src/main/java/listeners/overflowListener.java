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
        if (event.getJDA().getGuilds().size() > 25) {
            PrivateChannel pc = event.getGuild().getOwner().getUser().openPrivateChannel().complete();
            pc.sendMessage("Sorry but i am on over 25 Guilds so my Capacity is full! Maybe a other time!").queue();
            event.getGuild().leave();
            return;
        }
        Guild g = event.getGuild();
        if(!SQL.ifGuildExists(g)) {
            SQL.createServer(g);
            System.out.println("[Amme]System started on: " + g.getName());
        }


    }

}
