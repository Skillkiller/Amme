package commands.essentials;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.SQL;
import util.STATICS;
import util.embedSender;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 03.10.2017 17:44
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class settings implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        Guild guild = event.getGuild();
        MessageChannel channel = event.getTextChannel();
        if (core.permissionHandler.check(3, event)) return;
        switch (args[0]) {
            case "msg":
                if (args[1].equals("toggle")) {
                    String enabled = SQL.getValue(guild, "msg");

                    if(enabled.equals("1")){
                        SQL.updateValue(guild, "msg", "0");
                        embedSender.sendEmbed(":white_check_mark: Succesfully disabled Write Comment!", channel, Color.green);
                    } else if(enabled.equals("0")){
                        SQL.updateValue(guild, "msg", "1");
                        embedSender.sendEmbed(":white_check_mark: Succesfully enabled Write Comment!", channel, Color.green);
                    }

                }


        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("settings", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getTextChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: -settings <VALUES>";
    }

    @Override
    public String description() {
        return "Set Settings for the Guild!";
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
