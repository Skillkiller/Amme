package listeners;

import core.Main;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.SQL;

import javax.management.relation.RoleNotFoundException;

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
        Guild guild = event.getGuild();
        if(!SQL.ifGuildExists(guild))
            SQL.createServer(guild);
        if (event.getMember().getUser().isBot()) return;
        PrivateChannel pc = event.getMember().getUser().openPrivateChannel().complete();
        if(SQL.getValue(event.getGuild(), "autorole").equals("0")) {
            pc.sendMessage(
                    "**Hey,** " + event.getMember().getAsMention() + " and welcome on " + event.getGuild().getName() + " :wave:\n\n" +
                            "Now, have a nice day and a lot of fun on the server! ;)"
            ).queue();


        }else {
            try {
                event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRolesByName(SQL.getValue(event.getGuild(), "autorole"), true)).queue();
                pc.sendMessage(
                        "**Hey,** " + event.getMember().getAsMention() + " and welcome on " + event.getGuild().getName() + " :wave:\n\n" +
                                "You automatically got the Role `" + SQL.getValue(event.getGuild(), "autorole") + "` \n" +
                                "Now, have a nice day and a lot of fun on the server! ;)"
                ).queue();
            } catch (Exception e) {
                e.printStackTrace();
                PrivateChannel ow = event.getGuild().getOwner().getUser().openPrivateChannel().complete();
                ow.sendMessage("Please enter a valid Autorole Role!").queue();
                ow.sendMessage(Main.commands.get("settings").help() + "\n Only in Guild do not send commands at PM!");
            }
            String enabled = SQL.getValue(guild, "joinmessages");
            String channelid = SQL.getValue(guild, "joinchannel");
            String joinmsg = SQL.getValue(event.getGuild(), "joinmessage").replace("%USER%", event.getMember().getAsMention()).replace("%GUILD%", event.getGuild().getName());
            if (!enabled.equals("0")) {
            try {
                TextChannel channel = guild.getTextChannelById(channelid);
                channel.sendTyping().queue();
            channel.sendMessage(joinmsg).queue();
            } catch (PermissionException e) {
                e.printStackTrace();
            }}
        }
    }

}
