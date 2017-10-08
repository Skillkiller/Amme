package commands.Administration;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import core.*;
import util.Logger;
import util.STATICS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 28.09.2017 16:52
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class restart implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (permissionHandler.check(4, event)) return;
        event.getMessage().delete().queue();
        event.getTextChannel().sendMessage(":warning:  Rebooting Bot-System now!").queue();

        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            Runtime.getRuntime().exec("screen sudo python restart.py");
        else
            Runtime.getRuntime().exec("cmd.exe -restart");

        System.exit(0);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("restart", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getTextChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: -restart";
    }

    @Override
    public String description() {
        return "Restart the Bot";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.administration;
    }

    @Override
    public int permission() {
        return 3;
    }
}
