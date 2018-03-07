package commands.GuildAdmin;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 25.10.2017 14:33
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Clear implements Command{

    private int getInt(String string){
        try{
            return Integer.parseInt(string);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        event.getMessage().delete().queue();
        if(core.permissionHandler.check(2, event)) return;
        if (args.length < 1) util.embedSender.error().setDescription(help()).setTitle("ERROR!").build();
        int numb = getInt(args[0]);
        if (numb > 1 && numb <= 100) {
            try{
                MessageHistory history = new MessageHistory(event.getChannel());
                List<Message> msgs;
                msgs = history.retrievePast(numb).complete();
                event.getChannel().deleteMessages(msgs).queue();
               Message msg = event.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Color.GREEN)
                        .setDescription(":bomb: Deleted " + args[0] + " Messages!")
                        .build()
                ).complete();

               new Timer().schedule(new TimerTask() {
                   @Override
                   public void run() {
                       msg.delete().queue();
                   }
               }, 3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            util.embedSender.error().setDescription(help() + "Max. 100 Message and Min. 1 Message").setTitle("ERROR!").build();
        }
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("clear", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: _clear <AmountOfMessages>";
    }

    @Override
    public String description() {
        return "Clears the amount of Messages you want.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.guildadmin;
    }

    @Override
    public int permission() {
        return 2;
    }
}
