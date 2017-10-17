package listeners;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.SQL;

import static net.dv8tion.jda.core.OnlineStatus.ONLINE;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 29.09.2017 08:15
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class writecomment extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getMentionedUsers().size() < 1) return;
            if (event.getMessage().getMentionedUsers().get(0).equals(event.getJDA().getSelfUser())) {
                if (!SQL.ifGuildExists(event.getGuild())) {SQL.createServer(event.getGuild());}
                event.getChannel().sendMessage("Try **" + SQL.getValue(event.getGuild(), "prefix") + "help** for Help.").queue();
            }
        if (event.getAuthor().isBot()) return;
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        Guild g = event.getGuild();
        User user = event.getMessage().getMentionedUsers().get(0);
        String us = event.getGuild().getMember(user).getOnlineStatus().toString();
        if (event.getMessage().getMentionedUsers().size() < 1) return;
        if (SQL.getValue(g, "msg").equals("0")) return;
        if (!us.equals("ONLINE")){
            Message msg = event.getChannel().sendMessage("Hey, " + event.getAuthor().getAsMention() + " " + event.getMessage().getMentionedUsers().get(0).getAsMention() + " is " + us + " you can wait long to get an Answer!").complete();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg.delete().queue();
        }else return;
    }
}
