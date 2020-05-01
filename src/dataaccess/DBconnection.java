package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {

    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/finalproject";
    private static final String USERNAME = "Nui";
    private static final String PASSWORD = "nui";

    public static Connection getConnecting() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database driver doesn't exist");
        } catch (SQLException ex) {
            System.out.println("Can not connect to database");
        }
        return conn;
    }


}
