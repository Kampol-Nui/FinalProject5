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

    public static double SelectLastMoney(double id) {
        double money = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = ("SELECT * FROM CUSTOMERACCOUNT C1 WHERE C1.ORDER_NUMBER=(SELECT MAX(ORDER_NUMBER) FROM CUSTOMERACCOUNT C2 WHERE C1.ID = C2.ID) AND id=" + id);

            rs = stm.executeQuery(query);

            if (rs.next()) {

                money = rs.getDouble("MYMONEY");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return money;
    }
}
