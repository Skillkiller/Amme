package util;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

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
    private static String password = SECRETS.password;

    public static void connect(){
        if(!isConnected()){
            try{

                String host = STATICS.HOST;
                String port = STATICS.PORT;
                String database = STATICS.DATABASE;
                String username = STATICS.USERNAME;
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
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
            if(connection.isClosed())
                connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM guild WHERE serverid = ?");
            ps.setString(1, String.valueOf(guild.getIdLong()));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static void updateValue(Guild guild, String type, String value){
        try{
            if(connection.isClosed())
                connect();
            if(!ifGuildExists(guild))
                createServer(guild);
            PreparedStatement ps = connection.prepareStatement("UPDATE guild SET " + type + " = '" + value + "' WHERE serverid = " + guild.getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void createServer(Guild guild){
        try{
            if(connection.isClosed())
                connect();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `guild`(`serverid`,`joinchannel`, `prefix`, `logchannel`, `msg`, `autorole`, `joinmessage` ) VALUES (?, ?, '_', '0', '1', '0', 'Welcome %USER% on %GUILD%')");
            ps.setString(1, String.valueOf(guild.getIdLong()));
            ps.setString(2, guild.getDefaultChannel().getId());
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String getValue(Guild guild, String type){
        try{
            if(connection.isClosed())
                connect();
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