package listeners;

import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;
import util.SQL;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 27.09.2017 12:07
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class overflowListener extends ListenerAdapter{
    public void onGuildJoin(GuildJoinEvent event) {
        Guild g = event.getGuild();
            SQL.createServer(g);
            System.out.println("[Amme]System started on: " + g.getName());
        Guild guild = event.getGuild();
        GuildController controller = guild.getController();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                controller.createCategory("Amme").queue(cat -> {

                    controller.modifyCategoryPositions()
                            .selectPosition(cat.getPosition())
                            .moveTo(0).queue();

                    String[] list = {"music", "commands", "log", "randomstuff"};

                    Arrays.stream(list).forEach(s ->
                            controller.createTextChannel(s).queue(chan -> chan.getManager().setParent((Category) cat).queue())
                    );
                });
                SQL.updateValue(guild, "music", event.getGuild().getTextChannelsByName("music", true).get(0).getName());
                SQL.updateValue(guild, "logchannel", event.getGuild().getTextChannelsByName("log", true).get(0).getId());
                SQL.updateValue(guild, "joinchannel", event.getGuild().getTextChannelsByName("randomstuff", true).get(0).getId());
            }
        }, 5000);


    }

}
