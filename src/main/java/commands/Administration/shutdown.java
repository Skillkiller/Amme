package commands.Administration;

import commands.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.STATICS;
import core.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 27.09.2017 17:34
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class shutdown implements Command{


    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        if (event.getGuild().getOwner().getUser().getId().equals("221905671296253953")) {
        if (core.permissionHandler.check(1, event)) return;
        }else if (core.permissionHandler.check(4, event)) return;
        event.getMessage().delete().queue();
        event.getChannel().sendMessage(":battery: System going down!").queue();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000);
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("stop", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: _stop";
    }

    @Override
    public String description() {
        return "Emergency Shutdown the Bot";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.administration;
    }

    @Override
    public int permission() {
        return 2;
    }
}
