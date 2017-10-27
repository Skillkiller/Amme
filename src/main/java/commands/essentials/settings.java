package commands.essentials;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
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
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
        if (core.permissionHandler.check(3, event)) return;
        if (args.length < 2) event.getChannel().sendMessage(help());
        switch (args[0].toLowerCase()) {
            case "msg":
                if (args[1].toLowerCase().equals("toggle")) {
                    String enabled = SQL.getValue(guild, "msg");

                    if(enabled.equals("1")){
                        SQL.updateValue(guild, "msg", "0");
                        embedSender.sendEmbed(":white_check_mark: Succesfully disabled Write Comment!", channel, Color.green);
                    } else if(enabled.equals("0")){
                        SQL.updateValue(guild, "msg", "1");
                        embedSender.sendEmbed(":white_check_mark: Succesfully enabled Write Comment!", channel, Color.green);
                    }

                }
                break;
            case "prefix":
                if (args.length < 2) {
                    event.getChannel().sendMessage(help());
                    return;
                }
                SQL.updateValue(guild, "prefix", args[1]);
                embedSender.sendEmbed(":white_check_mark: Succesfully set the Prefix!", channel, Color.green);
                break;
            case "logchannel":
                if (args.length < 2) {
                    event.getChannel().sendMessage(help());
                    return;
                }
                String txt = event.getMessage().getMentionedChannels().get(0).getId();
                SQL.updateValue(guild, "logchannel", txt);
                embedSender.sendEmbed(":white_check_mark: Succesfully set the LogChannel!", channel, Color.green);
                break;
            case "autorole":
                if (args.length < 2) {
                    event.getChannel().sendMessage(help() + "\n(watch for large and lower case\n)");
                    return;
                }
                SQL.updateValue(guild, "autorole", args[1]);
                embedSender.sendEmbed(":white_check_mark: Succesfully set the Autorole!", channel, Color.green);
                break;
            case "joinmessage":
                if (args.length < 2) {
                    event.getChannel().sendMessage(help() + "\n(watch for large and lower case\n)");
                    return;
                }
                String message = String.join(" ", args).split(args[0])[0];
                SQL.updateValue(guild, "joinmessage", message);
                embedSender.sendEmbed(":white_check_mark: Succesfully set the Joinmessage!", channel, Color.green);
                break;
            case "joinmessagechannel":
                if (args.length < 2) {
                    event.getChannel().sendMessage(help() + "\n(watch for large and lower case\n)");
                    return;
                }
                String ch = event.getMessage().getMentionedChannels().get(0).getId();
                SQL.updateValue(guild, "joinchannel", ch);
                embedSender.sendEmbed(":white_check_mark: Succesfully set the Joinmessagechannel!", channel, Color.green);
                break;
        }
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("settings", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE:\n" +
                "_settings autorole <ROLENAME> (Set the Autorole at UserJoin | 0 for no Role.)\n" +
                "_settings logchannel <Mention channel> (Set the logchannel | 0 for no Channel.)\n" +
                "_settings prefix <NEWPREFIX> (Set the new Bot Prefix for this Guild)\n" +
                "_settings msg <Toogle> (Enable/Disable Writecomment)\n" +
                "_settings joinmessage <Message> (%USER% for the Username %GUILD% for Guildname) (0 for no message)\n" +
                "_settings joinmessagechannel <Channel> (Mention the channel for the Joinmessage)";
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
