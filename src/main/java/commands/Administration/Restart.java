package commands.Administration;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.STATICS;

import java.awt.*;
import java.io.IOException;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 08.11.2017 18:19
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Restart implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
    if (core.permissionHandler.check(4,event)) return;
        MessageChannel channel = event.getChannel();
        channel.sendTyping().queue();
        event.getMessage().delete().queue();

        util.embedSender.sendEmbed(":battery: System Restarting!", channel, Color.GREEN);

        //Code by ZekroTJA(github.com/ZekroTJA)
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            Runtime.getRuntime().exec("screen python restart.py");

        System.exit(0);

    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
    util.Logger.logCommand("restart", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");

    }

    @Override
    public String help() {
        return "USAGE: _restart";
    }

    @Override
    public String description() {
        return "RESTARTS THE BOT(only Lee)";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.administration;
    }

    @Override
    public int permission() {
        return 4;
    }
}
