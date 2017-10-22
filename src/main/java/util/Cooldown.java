package util;

import java.util.ArrayList;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 05.10.2017 19:41
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * - github.com/DRSchlaubi
 * <p>
 * © Coders Place 2017
 */
public class Cooldown {
    //Cooldown for Leveler
    public static ArrayList<String> ids = new ArrayList<>();


    public static boolean has(String id) {
        if (ids.contains(id)) {
            return true;
        }else {
            return false;
        }

    }

    public static void add(String id) {
        ids.add(id);
    }

    public static void remove(String id) {
        ids.remove(id);
    }
    //Cooldown Music
    public static ArrayList<String> idsv = new ArrayList<>();
    public static boolean hasv(String id) {
        if (idsv.contains(id)) {
            return true;
        }else {
            return false;
        }

    }

    public static void addv(String id) {
        idsv.add(id);
    }

    public static void removev(String id) {
        idsv.remove(id);
    }

}
