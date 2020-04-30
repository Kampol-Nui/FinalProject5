
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Connect {
    //private static final String DRIVER="org.apache.derby.jdbc.ClientDriver";

    private static final String URI = "jdbc:derby://localhost:1527/finalproject";
    private static final String USERNAME = "Nui";
    private static final String PASSWORD = "nui";

    public static Connection getCon() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return con;
    }
}
