package pl.com.frankiewicz.handel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    Connection connection = null;
    public static Connection dbConnector() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\sqlite\\main.db");
            JOptionPane.showMessageDialog(null, "Połączono");
            return  connection;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
