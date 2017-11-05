package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.STATICS;


import java.awt.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 27.10.2017 20:12
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class google implements Command{
    private String ttemp = "";
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        if (args.length < 1) {event.getChannel().sendMessage(help()); return;}
        String query = "";
        for(int i = 0; i < args.length; i++){
            query += " " + args[i];
        }
        String google = "http://www.google.com/search?q=";
        String search = query;
        String charset = "UTF-8";
        String userAgent = "Amme 7.5 (+http://lordlee.de)";

        Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");

        for (Element link : links) {
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }

            ttemp = ttemp + link.text().toString() + link.baseUri() +  "\n";

        }
        util.embedSender.sendEmbed("Search Results for **" + query + "**:\n" + ttemp,event.getChannel(), Color.GREEN);
    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: _google <QUERRY>";
    }

    @Override
    public String description() {
        return "Simple Google Command. Search the web i think.";
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
