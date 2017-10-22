package commands.etc;

import com.rosaloves.bitlyj.BitlyException;
import com.rosaloves.bitlyj.Url;
import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.*;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 15.10.2017 11:20
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class shorter implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        channel.sendTyping().queue();
        message.delete().queue();

        if(args.length > 0){
            try {
                PrivateChannel privch = author.openPrivateChannel().complete();
                Url bitlink = as(STATICS.BITLYUSERNAME, SECRETS.bitlytoken).call(shorten(args[0]));
                embedSender.sendEmbed(":white_check_mark: Successfully created shortlink " + bitlink.getShortUrl(), channel, Color.green);
                privch.sendTyping().queue();
                embedSender.sendEmbed(":ballot_box_with_check: You have successfully shortened the link `" + bitlink.getLongUrl() + "` \n to " + bitlink.getShortUrl(), privch, Color.green);
            } catch (BitlyException e){
                embedSender.sendEmbed(":warning: Please send an valid URL", channel, Color.yellow);
            }
        } else {
            embedSender.sendEmbed("Usage: `" + "*" + "short <URL>`", channel, Color.red);
        }
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("short", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");

    }

    @Override
    public String help() {
        return "USAGE: _short <URL>";
    }

    @Override
    public String description() {
        return "A simple URL shortener.";
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
