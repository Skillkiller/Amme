package commands.chat;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.LVL;
import util.*;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 21:06
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class getLvl implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        embedSender.sendEmbed(
           "Your current Level: \n" + LVL.getValue(event.getAuthor(), "level") + "\n" + "Your current Points: \n" + LVL.getValue(event.getAuthor(), "points")
                , event.getTextChannel(), Color.CYAN);

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("rank", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getTextChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: -rank";
    }

    @Override
    public String description() {
        return "Get your current level";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.chatutils;
    }

    @Override
    public int permission() {
        return 0;
    }
}
