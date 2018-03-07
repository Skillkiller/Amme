package commands.essentials;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Invite;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 05.11.2017 11:31
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Guild implements Command{
    Invite link;
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        List<Invite> i = event.getGuild().getInvites().complete();
        if(i.get(0).isTemporary() || i.get(0).toString().equals(null)){
           link = event.getChannel().createInvite().setTemporary(false).complete();
        } else {
            link = i.get(0);
        }
        event.getChannel().sendMessage(new EmbedBuilder()
        .setColor(Color.cyan)
        .setTitle("*Guild Info for* **" + event.getGuild().getName() + "**!")
        .setDescription("**Guild Invite** : " + link.getURL().toString() + "\n\n" + "**Total of Users** : " + event.getGuild().getMembers().size() + "\n\n" + "**The Owner is** : " + event.getGuild().getOwner().getAsMention() + "\n\n")
        .build()
        ).queue();
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        util.Logger.logCommand("guild",event);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String description() {
        return "A simple Guild Info";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.essentials;
    }

    @Override
    public int permission() {
        return 0;
    }
}
