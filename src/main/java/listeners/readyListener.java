package listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

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
public class readyListener extends ListenerAdapter {


    @Override
    public void onReady(ReadyEvent event){
        String out = "\n This bot is running on the following servers: \n";

        for (Guild g : event.getJDA().getGuilds()){
            out += g.getName() + " (" + g.getId() + ")  \n";
        }

        System.out.println(out);





    }
}
