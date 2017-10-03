package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 28.09.2017 17:31
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class say implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (core.permissionHandler.check(2, event)) return;

        String serverID = event.getGuild().getId();
        String channel = event.getTextChannel().getName();

        event.getMessage().delete().queue();

        String output = "";
        if (args.length > 0) {
            for (String e : args) {
                output += e + " ";
            }
        }

        event.getTextChannel().sendMessage(":speech_balloon:    " + output).queue();
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("say", event);
    }

    @Override
    public String help() {
        return "USAGE: -say [MESSAGE]";
    }

    @Override
    public String description() {
        return "Be the Bot!";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.chatutils;
    }

    @Override
    public int permission() {
        return 2;
    }
}
