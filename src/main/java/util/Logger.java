package util;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

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
    public static void logCommand(String command, GuildMessageReceivedEvent event){
        Guild guild = event.getGuild();
        String prefix = SQL.getValue(guild, "prefix");
        String logchannel = SQL.getValue(guild, "logchannel");
        if (SQL.getValue(guild, "logchannel").equals("0")) return;
        String us = event.getMember().getNickname();
        TextChannel channel = guild.getTextChannelById(logchannel);
        if(us == null) us = event.getAuthor().getName();
        embedSender.sendEmbed("[Command] `" + STATICS.PREFIX +  command + "` was executed by **" + us + " (" + event.getAuthor().getName()+ "#" + event.getAuthor().getDiscriminator() + ")**", channel, Color.cyan);
    }
}
