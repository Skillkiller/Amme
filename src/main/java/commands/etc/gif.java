package commands.etc;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.SECRETS;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 03.10.2017 16:57
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class gif implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        message.delete().queue();

        Message mymsg = channel.sendMessage(new EmbedBuilder().setDescription("Collecting data ...").setColor(Color.blue).build()).complete();
        Giphy giphy = new Giphy(SECRETS.giphykey);
        String arg = "";
        for (int i = 0; i < args.length; i++) {
            arg += args[i] + " ";
        }
        try {
            SearchFeed feed = giphy.search(arg, 1, 0);
            try {
                String gifurl = feed.getDataList().get(0).getImages().getOriginal().getUrl();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mymsg.delete().queue();
                        channel.sendMessage(gifurl).queue();
                    }
                },1000);
            } catch (IndexOutOfBoundsException e){
                mymsg.editMessage(new EmbedBuilder().setDescription("Sorry I can't find your gif .").setColor(Color.red).build()).queue();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mymsg.delete().queue();
                    }
                }, 5000);
            }

        } catch (GiphyException e) {
            mymsg.editMessage(new EmbedBuilder().setDescription("Sorry I can't find your gif .").setColor(Color.red).build()).queue();
            e.printStackTrace();
        }


    }


    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("gif", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: -gif <SEARCH QUERRY>";
    }

    @Override
    public String description() {
        return "Send Gif´s by search value!";
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
