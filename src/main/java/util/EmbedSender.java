package util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.awt.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 17.09.2017 14:58
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class EmbedSender {

    public static EmbedBuilder success() {
        return new EmbedBuilder().setColor(Color.green);
    }

    public static EmbedBuilder error() {
        return new EmbedBuilder().setColor(Color.red);
    }

    public static void sendEmbed(String content, MessageChannel channel, Color color){
        EmbedBuilder embed = new EmbedBuilder().setDescription(content).setColor(color);
        channel.sendMessage(embed.build()).complete();

    }

}
