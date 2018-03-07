package commands.etc;

import commands.Command;
import core.CoreCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONObject;
import util.*;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Amme JDA BOT
 * <p>
 * By LordLee at 22.10.2017 12:32
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class wheater implements Command{
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) throws ParseException, IOException {
        if (args.length < 2) {
            event.getChannel().sendMessage(help());
            return;
        }
        if (util.CooldownApi.has("1")) return;
        JSONObject json = JsonReader.readJsonFromURL("http://api.wunderground.com/api/" + SECRETS.wkey + "/geolookup/conditions/forecast/q/" + args[0] + "/" + args[1] + ".json");
        event.getMessage().delete().queue();
        JSONObject location = json.getJSONObject("location");
        String city = location.getString("city");
        JSONObject current_observation = json.getJSONObject("current_observation");
        String temperature_string = current_observation.getString("temperature_string");
        String weather = current_observation.getString("weather");
        String icon_url = current_observation.getString("icon_url");
        event.getChannel().sendMessage(new EmbedBuilder()
        .setColor(Color.GREEN)
        .setTitle(":sun_with_face: Weather in **" + city + "**")
        .addField("Temperature:", temperature_string, false)
        .addField("Weather:", weather, false)
        .setThumbnail(icon_url)
        .build()
        ).queue();
        util.CooldownApi.add("1");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                CooldownApi.remove("1");
            }
        }, 6000);


    }

    @Override
    public void executed(boolean success, GuildMessageReceivedEvent event) {
        Logger.logCommand("weather", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getChannel().getId() + ") ");

    }

    @Override
    public String help() {
        return "USAGE: _weather <Country> <CityName>";
    }

    @Override
    public String description() {
        return "See the weather.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.etc;
    }

    @Override
    public int permission() {
        return 0;
    }
}
