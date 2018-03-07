package core;

import commands.*;
import commands.Administration.Restart;
import commands.Administration.shutdown;
import commands.Administration.test;
import commands.GuildAdmin.Clear;
import commands.GuildAdmin.kick;
import commands.chat.*;
import commands.essentials.*;
import commands.etc.*;
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

    private static SQL sql;

    public static HashMap<String, Command> commands = new HashMap<>();

    public static final commandParser parser = new commandParser();

    public static JDA jda;

    public static void main(String[] args) throws IOException {
        //Code by ZekroTJA(github.com/ZekroTJA)
        StartArgumentHandler.args = args;
        //MY CODE
        builder = new JDABuilder(AccountType.BOT)
                .setToken(SECRETS.TOKEN)
                .setAudioEnabled(true)
                .setAutoReconnect(true)
                .setStatus(STATICS.STATUS)
                .setGame(Game.of(STATICS.CUSTOM_MESSAGE + " | _help | coded by Lee", "http://twitch.tv/lordleeyt"))
                ;

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
        commands.put("speedtest", new SpeedTest());
        commands.put("psay", new say());
        commands.put("startup", new guildstart());
        commands.put("rank", new getLvl());
        commands.put("github", new github());
        commands.put("gif", new gif());
        commands.put("lmgtfy", new lmgtfy());
        commands.put("settings", new settings());
        commands.put("info", new info());
        commands.put("uptime", new uptime());
        commands.put("fail", new fail());
        commands.put("short", new shorter());
        commands.put("kick", new kick());
        commands.put("ping", new ping());
        commands.put("weather", new wheater());
        commands.put("clear", new Clear());
        commands.put("vote", new Vote());
        commands.put("v", new Vote());
        commands.put("google", new google());
        commands.put("guild", new Guild());
        commands.put("restart", new Restart());
        commands.put("userinfo", new UserInfo());
        commands.put("bday", new bday());
        commands.put("test", new test());
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
        builder.addEventListener(new ReactionListener());
    }
    public static void handleCommand(commandParser.CommandContainer cmd) throws IOException, ParseException {

        if (commands.containsKey(cmd.invoke.toLowerCase())) {

            boolean safe = commands.get(cmd.invoke.toLowerCase()).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke.toLowerCase()).action(cmd.args, cmd.event);
                commands.get(cmd.invoke.toLowerCase()).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke.toLowerCase()).executed(safe, cmd.event);
            }



        }}

}
