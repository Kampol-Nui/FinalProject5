package dataaccess;

import genarate.TimeStamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import service.CustomerAccount;
import service.TopupStatus;


public class DBmanager {

    public static void keepCustomerInfo(CustomerAccount ac) {
        String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(id,username,password,mymoney)" + "VALUES(?,?,?,?)";
        try (Connection conn = DBconnection.getConnecting();) {
            try (PreparedStatement pstm = conn.prepareStatement(sql1);) {
                pstm.setDouble(1, ac.getUniqueId());
                pstm.setString(2, ac.getUsername());
                pstm.setString(3, ac.getPassword());
                pstm.setDouble(4, ac.getMyMoney());
                pstm.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void topupMoney(CustomerAccount ac) {
        double lastMoney;
        String sql1 = "INSERT INTO TOPUPBIll " + "(timestamp,id,username,topup,topupstatus)" + "VALUES(?,?,?,?,?)";
        //String sql2 = "UPDATE CUSTOMERACCOUNT set mymoney=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement stm = con.prepareStatement(sql1);) {
                stm.setString(1, new TimeStamp().toString());
                stm.setLong(2, ac.getUniqueId());
                stm.setString(3, ac.getUsername());
                stm.setDouble(4, ac.getTopupMoney());
                stm.setString(5, TopupStatus.SUCCESSFUL.name());
                stm.executeUpdate();
                lastMoney = ac.getTopupMoney() + SelectLastMoney(ac);
                System.out.println(lastMoney);
                String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + lastMoney + " WHERE id =" + ac.getUniqueId();
                try (Statement stmm = con.createStatement();) {
                    stmm.executeUpdate(sql2);
                    System.out.println("เติมเงินเสร็จสมบูรณ์");

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void SelectPurchaseHistory(CustomerAccount ac) {
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            String query = ("SELECT * FROM PURCHASEHISTORY WHERE ID =" + ac.getUniqueId());
            rs = stm.executeQuery(query);
            System.out.println("================================================================================================================");
            System.out.println(String.format("%11s %s %10s %s %20s %s %20s %s %14s %s %s", "DATETIME", "|", "ID", "|", "USERNAME", "|", "GAME", "|", "TOTALPRICE", "|", "MYMONEY"));
            System.out.println("================================================================================================================");
            while (rs.next()) {
                String dateTime = rs.getString("timestamp");
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String game = rs.getString("game");
                double totalprice = rs.getDouble("totalprice");
                double mymoney = rs.getDouble("mymoney");
                System.out.println(String.format("%11s %s %10s %s %20s %s %20s %s %14s %s %s", dateTime, "|", id, "|", username, "|", game, "|", totalprice, "|", mymoney));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }

    public static void PurchaseGame(CustomerAccount ac) {
        String sql1 = "INSERT INTO PURCHASEHISTORY " + "(timestamp,id,username,game,totalprice,mymoney)" + "VALUES(?,?,?,?,?,?)";
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement pstm = con.prepareStatement(sql1);) {
                pstm.setString(1, new TimeStamp().toString());
                pstm.setDouble(2, ac.getUniqueId());
                pstm.setString(3, ac.getUsername());
                pstm.setString(4, ac.getMyCart().getGameName());
                pstm.setDouble(5, ac.getMyCart().getTotalprice());
                pstm.setDouble(6, ac.getMyMoney());

                pstm.executeUpdate();

            } catch (SQLException ex) {
                ex.getMessage();
            }
            String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
            try (Statement stm = con.createStatement();) {
                stm.executeUpdate(sql2);
                System.out.println("ชำระเงินเสร็จสมบูรณ์ โปรดตรวจสอบ Library ของคุณหลังชำระเงิน ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        double lastMoney;
//        String sql1 = "INSERT INTO PURCHASEHISTORY " + "(timestamp,id,username,game,totalprice,mymoney)" + "VALUES(?,?,?,?,?)";
//        //String sql2 = "UPDATE CUSTOMERACCOUNT set mymoney=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
//        try (Connection con = DBconnection.getConnecting();) {
//            try (
//                    PreparedStatement stm = con.prepareStatement(sql1);) {
//                stm.setString(1, new TimeStamp().toString());
//                stm.setLong(2, ac.getUniqueId());
//                stm.setString(3, ac.getUsername());
//                stm.setDouble(4, ac.getTopupMoney());
//                stm.setString(5, TopupStatus.SUCCESSFUL.name());
//                stm.executeUpdate();
//                lastMoney = ac.getTopupMoney()+ SelectLastMoney(ac);
//                System.out.println(lastMoney);
//                String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + lastMoney + " WHERE id =" + ac.getUniqueId();
//                try (Statement stmm = con.createStatement();) {
//                    stmm.executeUpdate(sql2);
//                    System.out.println("เติมเงินเสร็จสมบูรณ์");
//                    
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            } catch (SQLException ex) {
//                ex.getMessage();
//            }
//        } catch (SQLException ex) {
//            ex.getMessage();
//        }
    }

    public static double SelectLastMoney(CustomerAccount ac) {
        double money = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            //String query = ("SELECT * FROM CUSTOMERACCOUNT C1 WHERE C1.ORDER_NUMBER=(SELECT MAX(ORDER_NUMBER) FROM CUSTOMERACCOUNT C2 WHERE C1.ID = C2.ID) AND id=" + ac.getUniqueId());
            String query2 = "SELECT * FROM CUSTOMERACCOUNT WHERE ID=" + ac.getUniqueId();
            rs = stm.executeQuery(query2);

            if (rs.next()) {

                money = rs.getDouble("MYMONEY");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return money;
    }

    public static void CreateTable() {
        try (Connection con = dataaccess.DBconnection.getConnecting();
                Statement stm = con.createStatement()) {
            try {
                stm.executeUpdate("DROP TABLE CUSTOMERACCOUNT");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE TOPUPBILL");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE PURCHASEHISTORY");
            } catch (SQLException ex) {
            }
            //try {stm.executeUpdate("CREATE TABLE customer (cus_id INT NOT NULL, cus_name VARCHAR(100),PRIMARY KEY (cus_id))");} catch (SQLException ex) {} 
            try {
                stm.executeUpdate("CREATE TABLE CUSTOMERACCOUNT (ID BIGINT not null primary key,USERNAME VARCHAR(50),PASSWORD VARCHAR(50),MYMONEY DOUBLE)");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE TOPUPBILL (ORDER_NUMBER INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),"
                        + " TIMESTAMP VARCHAR(50),ID BIGINT,USERNAME VARCHAR(50),TOPUP DOUBLE,TOPUPSTATUS VARCHAR(50))");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE PURCHASEHISTORY (ORDER_NUMBER INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),"
                        + " TIMESTAMP VARCHAR(50),ID BIGINT,USERNAME VARCHAR(50),GAME VARCHAR(50),TOTALPRICE DOUBLE,MYMONEY DOUBLE)");
            } catch (SQLException ex) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void selectAllCustomer() {
        long id = 0;
        String username = null;
        String password = null;
        double myMoney = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = "SELECT * FROM CUSTOMERACCOUNT";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                id = rs.getLong("ID");
                username = rs.getString("USERNAME");
                password = rs.getString("PASSWORD");
                myMoney = rs.getDouble("MYMONEY");
            }
            System.out.println("========================================================================");
            System.out.println(String.format("%10s %s %20s %s %20s %s %10s " , "ID", "|", "USERNAME", "|", "PASSWORD", "|", "MYMONEY"));
            System.out.println("========================================================================");
            System.out.println(String.format("%10s %s %20s %s %20s %s %s ", id, "|", username, "|", password, "|", myMoney));
            System.out.println("------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
