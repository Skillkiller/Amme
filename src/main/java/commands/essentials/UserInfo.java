package commands.essentials;


import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 08.11.2017 18:38
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class UserInfo implements Command {
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        //CODE BY SCHLAUBI
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        Guild guild =event.getGuild();
        channel.sendTyping().queue();
        message.delete().queue();


        Member member;
        if (args.length > 0){
            //DANke SCHLAUBI
            member = guild.getMember(event.getMessage().getMentionedUsers().get(0));
        }else
            member = guild.getMember(event.getAuthor());
        String NAME = member.getEffectiveName();
        String TAG = member.getUser().getName() + "#" + member.getUser().getDiscriminator();
        String ID = member.getUser().getId();
        String STATUS = member.getOnlineStatus().getKey();
        String ROLES = "";
        String GAME;
        String AVATAR = member.getUser().getAvatarUrl();
        String GUILDDATE = member.getJoinDate().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String JOINDATE = member.getUser().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME);

        try {
            GAME = member.getGame().getName();
        } catch (Exception e){
            GAME = "~";
        }
        if(AVATAR == null){
            AVATAR = "No avatar";
        }
        for(Role role : member.getRoles()){
            ROLES += role.getName() + ", ";
        }
        if(ROLES.length() > 0){
            ROLES = ROLES.substring(0, ROLES.length()-2);
        } else {
            ROLES = "NO ROLES";
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(Color.cyan)
                .setDescription(":spy: UserInfo for " + NAME + " (" + TAG + ")")
                .addField("Name/Nick", NAME, false)
                .addField("User tag", TAG, false)
                .addField("User id", ID , false)
                .addField("Current status", STATUS, false)
                .addField("Current game", GAME, false)
                .addField("Guild joined", GUILDDATE, false)
                .addField("Roles", ROLES, false)
                .addField("Dicord joined", JOINDATE, false)
                .addField("Avatar url", AVATAR, false);
        if(AVATAR != "No avatar"){
            embed.setThumbnail(AVATAR);
        }
        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("UserInfo",event);
    }

    @Override
    public String help() {
        return "USAGE: _userinfo (MENTION)";
    }

    @Override
    public String description() {
        return "Just a typical UserInfo";
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
