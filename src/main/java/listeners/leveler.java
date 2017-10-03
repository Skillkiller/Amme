package listeners;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.*;

import java.awt.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 21:35
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class leveler extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (LVL.ifUserExists(event.getAuthor())) {
            int current = Integer.parseInt(LVL.getValue(event.getAuthor(), "points"));
            String point = String.valueOf(current+20);
            LVL.updateValue(event.getAuthor(), "points", point);
            String lvlnow = LVL.getValue(event.getAuthor(), "level");
            int dann = Integer.parseInt(lvlnow);
            dann++;
            String fina = String.valueOf(dann);
            if (point.matches("1000")) {
                LVL.updateValue(event.getAuthor(), "level", fina);
                LVL.updateValue(event.getAuthor(), "points", "0");
                embedSender.sendEmbed(event.getAuthor().getAsMention() + " ,Wow you got a level up to Level **" + LVL.getValue(event.getAuthor(), "level") + "** !", event.getMessage().getTextChannel(), Color.GREEN);
            }

        }else LVL.createUser(event.getAuthor());
    }
}
