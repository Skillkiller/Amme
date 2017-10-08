package core;

import commands.Administration.restart;
import commands.Administration.shutdown;
import commands.Command;
import commands.chat.getLvl;
import commands.chat.info;
import commands.chat.say;
import commands.essentials.github;
import commands.essentials.guildstart;
import commands.essentials.help;
import commands.essentials.settings;
import commands.etc.SpeedTest;
import commands.etc.gif;
import commands.etc.lmgtfy;
import commands.etc.uptime;
import commands.music.Music;
import listeners.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import util.LVL;
import util.SECRETS;
import util.SQL;
import util.STATICS;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 15.09.2017 18:20
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class Main {
    static JDABuilder builder;

    public static HashMap<String, Command> commands = new HashMap<>();

    public static final commandParser parser = new commandParser();

    public static JDA jda;

    public static void main(String[] args) throws IOException {

        builder = new JDABuilder(AccountType.BOT)
                .setToken(SECRETS.TOKEN)
                .setAudioEnabled(true)
                .setAutoReconnect(true)
                .setStatus(STATICS.STATUS)
                .setGame(Game.of(STATICS.CUSTOM_MESSAGE + " | *help | coded by Lee", "http://twitch.tv/lordleeyt"));

        initializeListeners();
        initializeCommands();
        SQL.connect();
        LVL.connect();


        try {
            builder.buildBlocking();
        } catch (InterruptedException | RateLimitedException | LoginException e) {
            e.printStackTrace();
        }

    }

    private static void initializeCommands() {
        commands.put("music", new Music());
        commands.put("m", new Music());
        commands.put("help", new help());
        commands.put("stop", new shutdown());
        commands.put("restart", new restart());
        commands.put("speedtest", new SpeedTest());
        commands.put("say", new say());
        commands.put("startup", new guildstart());
        commands.put("rank", new getLvl());
        commands.put("github", new github());
        commands.put("gif", new gif());
        commands.put("lmgtfy", new lmgtfy());
        commands.put("settings", new settings());
        commands.put("info", new info());
        commands.put("uptime", new uptime());
    }
    private static void initializeListeners() {
        builder.addEventListener(new commandListener());
        builder.addEventListener(new readyListener());
        builder.addEventListener(new privateMessage());
        builder.addEventListener(new overflowListener());
        builder.addEventListener(new guildJoin());
        builder.addEventListener(new userstatus());
        builder.addEventListener(new writecomment());
        builder.addEventListener(new Usercreate());
        builder.addEventListener(new leveler());
        builder.addEventListener(new reconnectlistener());

    }
    public static void handleCommand(commandParser.CommandContainer cmd) throws ParseException, IOException {

        if (commands.containsKey(cmd.invoke)) {

            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }

        }
    }


}
