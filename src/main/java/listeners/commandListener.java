package listeners;


import core.Main;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATICS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static sun.rmi.transport.TransportConstants.Ping;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 17.09.2017 14:54
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class commandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getChannelType().equals(ChannelType.PRIVATE)) return;

        if (e.getMessage().getContent().startsWith(STATICS.PREFIX) && e.getMessage().getAuthor().getId() != e.getJDA().getSelfUser().getId()) {
            try {
                Main.handleCommand(Main.parser.parse(e.getMessage().getContent(), e));
                ArrayList<String> list = new ArrayList<>();
                list.add(e.getGuild().getId());
                list.add(CoreCommands.getCurrentSystemTime());
                list.add(e.getMember().getEffectiveName());
                list.add(e.getMessage().getContent());
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    }


