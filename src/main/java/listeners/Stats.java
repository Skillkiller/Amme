package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.SQL;

import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 12.11.2017 11:25
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Stats extends ListenerAdapter{


    private int count =  0;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String en = SQL.getValue(event.getGuild(),"joinchannel");
        Timer timer = new Timer();
        Date date=new Date();
        if (!event.getAuthor().isBot() && event.getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            count++;
        }

        timer.schedule(new TimerTask(){
            public void run(){
                if (!en.equals("0")) {
                    event.getJDA().getTextChannelById(en).sendMessage("Today i count " + count + " Messages!").queue();
                }
            }
        },date, 24*60*60*1000);



    }

}
