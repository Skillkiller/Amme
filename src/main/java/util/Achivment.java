package util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.ArrayList;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 05.10.2017 19:12
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Achivment {
    public static void level10(User user, MessageChannel channel) {
        channel.sendMessage(new EmbedBuilder()

                .setThumbnail("http://www.bilder-upload.eu/upload/910508-1507225009.png")
                .setColor(Color.RED)
                .setTitle("Wow,  " + user.getName() + " you Got Level 10! \n")
                .setDescription("You got ...(WIP)[Nothing]")
                .build()

        ).queue();
        LVL.updateValue(user, "lvl10", "1");
    }

}
