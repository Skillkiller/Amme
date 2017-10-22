package commands.chat;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 04.10.2017 11:02
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class info implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        event.getChannel().sendMessage(
                new EmbedBuilder()
                .setDescription(":robot: **Amme JDA Discord Bot**")
                .setThumbnail("https://raw.githubusercontent.com/LeeDJD/Amme/master/web/49380e9253a3ef3041f02804ca10c21d.jpg")
                .addField("Information",
                        "Github: https://github.com/LeeDJD/Amme \n\n" + "Owner/Developer:" + event.getJDA().getUserById("153507094933274624").getAsMention(), false)
                .addBlankField(false)
                .addField("Contributors for the Bot", "Zekro: \n" + "https://github.com/zekroTJA \n\n" + "Schlaubi: \n" + "https://github.com/DRSchlaubi \n\n", false)
                .addBlankField(false)
                .addField("Bot Invite Link", "[Invite](https://discordapp.com/oauth2/authorize?client_id=358288923065319426&scope=bot&permissions=0)", false)
                        .build()

        ).queue();
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("info", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: _info";
    }

    @Override
    public String description() {
        return "See Bot Information.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.chatutils;
    }

    @Override
    public int permission() {
        return 0;
    }
}
