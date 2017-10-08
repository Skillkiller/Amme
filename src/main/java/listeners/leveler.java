package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.*;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
        if (Cooldown.has(event.getAuthor().getId())) return;

        if (LVL.ifUserExists(event.getAuthor())) {
            //Point System
            int current = Integer.parseInt(LVL.getValue(event.getAuthor(), "points"));
            int zufallszahl = (int) ((Math.random() * 10) + 10);
            String point = String.valueOf(current+zufallszahl);
            int points = current+zufallszahl;
            LVL.updateValue(event.getAuthor(), "points", point);
            //Cooldown
            Cooldown.add(event.getAuthor().getId());
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Cooldown.remove(event.getAuthor().getId());
                }
            }, 30000);

            String lvlnow = LVL.getValue(event.getAuthor(), "level");
            int dann = Integer.parseInt(lvlnow);
            int req = dann*30;

            if (points>req) {
                dann++;
                String fina = String.valueOf(dann);
                LVL.updateValue(event.getAuthor(), "level", fina);
                LVL.updateValue(event.getAuthor(), "points", "0");
                String l = ( LVL.getValue(event.getAuthor(), "level"));
                int foo = Integer.parseInt(l);
                //Level Up
                Message msg = event.getChannel().sendMessage(new EmbedBuilder()
                        .setDescription(event.getAuthor().getAsMention() + " ,wow you got a Level up to Level **" + LVL.getValue(event.getAuthor(), "level") + "** !")
                        .build()
                ).complete();

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        msg.delete().queue();
                    }
                }, 10000);

                if (foo>10) {
                    //Level 10 Achivment
                    Achivment.level10(event.getMember().getUser(), event.getChannel());
                }
            }

        }else LVL.createUser(event.getAuthor());
    }
}
