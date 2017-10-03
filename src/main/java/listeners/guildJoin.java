package listeners;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 27.09.2017 12:11
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class guildJoin extends ListenerAdapter{
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if(event.getGuild().getOwner().getUser().getId().equals("221905671296253953")) return;
        try {
            event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRolesByName("Member", true)).queue();
        }catch (Exception e) {
            e.printStackTrace();
           PrivateChannel ow = event.getGuild().getOwner().getUser().openPrivateChannel().complete();
           ow.sendMessage("Please create a role with the name Member!").queue();
        }
        PrivateChannel pc = event.getMember().getUser().openPrivateChannel().complete();
        pc.sendMessage(
                "**Hey,** " + event.getMember().getAsMention() + " **and welcome on " + event.getGuild().getName() + " :wave:\n\n" +
                        "You automatically got assigned the server role Member" +  "` by me.\n\n" +
                        "Now, have a nice day and a lot of fun on the server! ;)"
        ).queue();
    }

}
