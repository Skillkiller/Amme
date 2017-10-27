package util;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

import java.sql.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 16:52
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class LVL {
    private static Connection connection;
    private static String password = SECRETS.password;
    public static void connect(){
        if(!isConnected()){
            try{
                String host = STATICS.HOST;
                String port = STATICS.PORT;
                String database = STATICS.DATABASE;
                String username = STATICS.USERNAME;
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
                System.out.println("[Amme] Leveler connected");

            } catch (SQLException e) {
                System.out.println("[Amme] Leveler connection failed");
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected(){
        return (connection != null);
    }

    public static boolean ifUserExists(User user){

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM schlaubibot WHERE userid = ?");
            ps.setString(1, user.getId());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static void createUser(User user){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `schlaubibot`(`userid`, `points`, `level`, `lvl10`, `bday`) VALUES ( ?, 1, 0, 0, 0)");
            ps.setString(1, user.getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateValue(User user, String type, String value){
        if(!ifUserExists(user))
            createUser(user);
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE schlaubibot SET " + type + " = '" + value + "' WHERE userid = " + user.getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String getValue(User user, String type){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM schlaubibot WHERE `userid` = ?");
            ps.setString(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getString(type);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
