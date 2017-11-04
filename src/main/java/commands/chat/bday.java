package commands.chat;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.LVL;
import util.Logger;
import util.SQL;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 20.10.2017 21:09
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class bday implements Command{
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd:MM");
        Date date = new Date();
        return dateFormat.format(date);
    }



    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        if (args.length < 1) {event.getChannel().sendMessage(help()).queue();
        return;
        }
        switch (args[0]) {
            case "set":
                LVL.updateValue(event.getAuthor(), "bday", args[1]);
                return;
        }
        User user = event.getAuthor();
            String str_date =  LVL.getValue(user, "bday");
            if (str_date.equals("0")) {
                event.getChannel().sendMessage(help());
                return;
            }
            DateFormat formatter ;
            Date d ;
            formatter = new SimpleDateFormat("dd:MM");
            d = formatter.parse(str_date);


        Date date=new Date();
        Timer timer = new Timer();


        timer.schedule(new TimerTask(){
            public void run(){
                if (getDateTime().equals(d)){
                    event.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder()
                            .setColor(Color.CYAN)
                            .setTitle(":cake: Happy Birthday " + user.getAsMention() + " :cake:")
                            .setAuthor(user.getName(), "", user.getAvatarUrl())
                            .setDescription("Hey @here ,\n" + user.getName() + "has his Birthday Today!")
                    .build()
                    ).queue();
                }
            }
        },date, 24*60*60*1000);

    }



    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("bday", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");

    }

    @Override
    public String help() {
        return "USAGE: _bday set dd:MM (example: 19:12)\n" + "";
    }

    @Override
    public String description() {
        return "Little Birthday Reminder";
    }

    @Override
    public String commandType() {
        return null;
    }

    @Override
    public int permission() {
        return 0;
    }
}
