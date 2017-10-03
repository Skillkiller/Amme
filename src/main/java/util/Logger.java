package util;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 03.10.2017 13:15
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class Logger {
    public static void logCommand(String command, MessageReceivedEvent event){
        Guild guild = event.getGuild();
        if (!guild.getOwner().getUser().getId().equals("221905671296253953")) return;


        TextChannel channel = guild.getTextChannelById("364425303088431106");
        embedSender.sendEmbed("[Command] `" + STATICS.PREFIX +  command + "` was executed by **" + event.getMember().getNickname() + " (" + event.getAuthor().getName()+ "#" + event.getAuthor().getDiscriminator() + ")**", channel, Color.cyan);
    }
}
