package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 29.09.2017 08:01
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class userstatus extends ListenerAdapter{
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
        OnlineStatus on = event.getGuild().getMember(event.getUser()).getOnlineStatus();
        if (event.getGuild().getOwner().getUser().getId().equals("153507094933274624")){
            event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage(
                    new EmbedBuilder()
                            .setColor(Color.cyan)
                    .setDescription("User **" + event.getUser().getAsMention() + "** is now **" + on + "**!")
                    .build()
            ).queue();

        }else return;

    }
}
