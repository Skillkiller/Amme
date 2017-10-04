package commands.etc;

import commands.Command;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.STATICS;
import util.embedSender;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 03.10.2017 17:25
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class lmgtfy implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        message.delete().queue();

        if(args.length > 0){
            String query = "";
            for(int i = 0; i < args.length; i++){
                query += " " + args[i];
            }
            String url = "http://lmgtfy.com/?iie=1&q=" + query.replace( " ", "%20");

            embedSender.sendEmbed("Link created send the following link to the person which needs help " + url, channel, Color.green);
        }}

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("lmgtfy", event);
    }

    @Override
    public String help() {
        return "USAGE: -lmgtfy <SEARCH QUERRY>";
    }

    @Override
    public String description() {
        return "Creates a Lmgtfy link for a person which needs help";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.etc;
    }

    @Override
    public int permission() {
        return 0;
    }
}
