package listeners;


import core.Main;
import core.CoreCommands;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.SQL;
import util.STATICS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static core.Main.handleCommand;
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
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Guild g = event.getGuild();
        if (!SQL.ifGuildExists(g)){
                SQL.createServer(g);
        return;}
        String prefix = SQL.getValue(g, "prefix");
        if (event.getMessage().getContent().startsWith(prefix) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            try {
                handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    }


