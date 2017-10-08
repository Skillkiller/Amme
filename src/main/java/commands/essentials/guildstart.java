package commands.essentials;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.SQL;
import util.STATICS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 13:24
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class guildstart implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (core.permissionHandler.check(3, event)) return;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Guild g = event.getGuild();
        if(!SQL.ifGuildExists(g)) {
            SQL.createServer(g);
            System.out.println("[Amme]System started on: " + g.getName());
            event.getTextChannel().sendMessage("System Online!").queue();
        }
        Logger.logCommand("startup", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getTextChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE:-startup";
    }

    @Override
    public String description() {
        return "Start the System. Only once";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.essentials;
    }

    @Override
    public int permission() {
        return 3;
    }
}
