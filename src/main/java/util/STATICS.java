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


    public static OnlineStatus STATUS = OnlineStatus.ONLINE;

    public static String CUSTOM_MESSAGE = "アメリ";

    public static final String[] FULLPERMS = {"Administrator", "Admin", "Moderator", "Supporter"};

    public static final String[] PERMS = {"Bot Owner", "Tuhle Typen", ""};

    public static String PREFIX = "*";

    public static String input;

    public static int music_volume = 0;

    public static int reconnectCount = 0;

    public static Date lastRestart;

    public class CMDTYPE {
        public static final String administration = "Administration";
        public static final String chatutils = "Chat Utilities";
        public static final String essentials = "Essentials";
        public static final String etc = "Etc";
        public static final String music = "Music";
        public static final String guildadmin = "Guild Administration";
    }


}
