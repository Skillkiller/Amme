package commands.chat;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 14.10.2017 21:39
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class fail implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        String em = event.getJDA().getGuildById("307084334198816769").getEmotesByName("fail",  true).get(0).getName();
        String e = event.getJDA().getGuildById("307084334198816769").getEmotesByName("fail",  true).get(0).getId();
        event.getChannel().sendMessage("<:" + em + ":" + e + ">").queue();
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("fail", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: _fail";
    }

    @Override
    public String description() {
        return "Returns a fail-Emoji.";
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
