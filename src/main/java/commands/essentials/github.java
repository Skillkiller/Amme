package commands.essentials;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.*;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 03.10.2017 13:07
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class github implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        embedSender.sendEmbed("The Github profile of <@153507094933274624> : https://github.com/LeeDJD \n The Coders Place(Github Organisation) : https://github.com/orgs/Coders-Place \n Send <@153507094933274624> or <@358288923065319426> a PM with your UserName so we can Invite you ^^.", event.getChannel(), Color.BLUE);
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("github", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: _github";
    }

    @Override
    public String description() {
        return "Displays Github Information";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.essentials;
    }

    @Override
    public int permission() {
        return 0;
    }
}
