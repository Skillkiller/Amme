package core;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 26.09.2017 19:28
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class CoreCommands {

    public static String getCurrentSystemTime() {
        DateFormat dateFormat = new SimpleDateFormat("[dd.MM.yyyy - HH:mm:ss]");
        Date date = new Date();

        return dateFormat.format(date);
    }
}
