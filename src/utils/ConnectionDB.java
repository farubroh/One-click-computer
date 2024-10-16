package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    Connection connection = null;

    public static Connection conDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/oneclickcomputers", "root", "");
            return con;
        } catch (Exception ex) {
            System.err.println("ConnectionDB : "+ex.getMessage());
            return null;
        }
    }
     
}
