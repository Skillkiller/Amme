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
public class CooldownApi {
    //Cooldown for Weather api
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


}
