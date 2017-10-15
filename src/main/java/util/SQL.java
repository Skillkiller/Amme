package util;

import net.dv8tion.jda.core.entities.Guild;

import java.sql.*;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 02.10.2017 13:04
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class SQL {

    private static Connection connection;
    public static void connect(){
        if(!isConnected()){
            try{


                connection = DriverManager.getConnection("jdbc:sqlite:lvl.sqlite");
                System.out.println("[Amme] GuildLogger Online");

            } catch (SQLException e) {
                System.out.println("[Amme] GuildLogger connection failed");
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected(){
        return (connection != null);
    }

    public static boolean ifGuildExists(Guild guild){

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM guild WHERE serverid = ?");
            ps.setString(1, guild.getId());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static void createServer(Guild guild){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `guild`(`ownerid`, `serverid`, `msg`) VALUES ( ?, ?, 1)");
            ps.setString(1, guild.getOwner().getUser().getId());
            ps.setString(2, guild.getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateValue(Guild guild, String type, String value){
        if(!ifGuildExists(guild))
            createServer(guild);
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE guild SET " + type + " = '" + value + "' WHERE serverid = " + guild.getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String getValue(Guild guild, String type){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM guild WHERE `serverid` = ?");
            ps.setString(1, guild.getId());
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
