package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.*;

import java.awt.*;
import java.util.Arrays;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 24.09.2017 13:15
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class permissionHandler {


    public static int getLvl(Member member) {
        if (member.getUser().getId().equals("153507094933274624"))
            return 4;
        if (member.equals(member.getGuild().getOwner()))
            return 3;
        if (member.getRoles().stream().anyMatch(role -> Arrays.stream(STATICS.FULLPERMS).anyMatch(s1 -> role.getName().equals(s1)))) {
            return 2;
        } else if (member.getRoles().stream().anyMatch(role -> Arrays.stream(STATICS.PERMS).anyMatch(s -> role.getName().equals(s)))) {
            return 1;
        }
        return 0;
    }

    public static boolean check(int required, MessageReceivedEvent event) {
        if (required > getLvl(event.getMember())) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(
                    "Sorry but you need permission level `" + required + "` or above!\n(Your current permission level is `" + getLvl(event.getMember()) + "`)."
            ).build()).queue();
            return true;
        }
        return false;
    }



}
