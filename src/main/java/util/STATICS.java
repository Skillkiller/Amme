package util;

import net.dv8tion.jda.core.OnlineStatus;

import java.util.Date;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 15.09.2017 18:22
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class STATICS {

    public static String PORT = "3306";

    public static String HOST = SECRETS.HOST;

    public static String DATABASE = "Discord";

    public static  String USERNAME = SECRETS.USERNAME;

    public static OnlineStatus STATUS = OnlineStatus.ONLINE;

    public static String CUSTOM_MESSAGE = "アメリ";

    public static final String[] FULLPERMS = {"Administrator", "Admin", "Moderator", "Supporter"};

    public static final String[] PERMS = {"Bot Owner", "Tuhle Typen"};

    public static String PREFIX = "*";

    public static String input;

    public static int music_volume = 10;

    public static int reconnectCount = 0;

    public static Date lastRestart;

    public static final String BITLYUSERNAME = SECRETS.bitlyuser;

    public class CMDTYPE {
        public static final String administration = "Administration";
        public static final String chatutils = "Chat Utilities";
        public static final String essentials = "Essentials";
        public static final String etc = "Etc";
        public static final String music = "Music";
        public static final String guildadmin = "Guild Administration";
    }




}
