package dataaccess;

import genarate.TimeStamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import service.CustomerAccount;
import service.TopupStatus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MINI
 */
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
        String sql1 = "INSERT INTO PURCHASEHISTORY " + "(timestamp,id,username,topup,topupstatus)" + "VALUES(?,?,?,?,?)";

        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement stm = con.prepareStatement(sql1);) {
                stm.setString(1, TimeStamp.getFormattedDate());
                stm.setLong(2, ac.getUniqueId());
                stm.setString(3, ac.getUsername());
                stm.setDouble(4, ac.getTopupMoney());
                stm.setObject(5, TopupStatus.SUCCESSFUL);
                stm.executeUpdate();

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
        String sql1 = "INSERT INTO PURCHASEHISTORY " + "(timestamp,id,username,game,totalprice,mymoney)" + "VALUES(?,?,?,?,?)";
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement pstm = con.prepareStatement(sql1);) {
                pstm.setString(1, TimeStamp.getFormattedDate());
                pstm.setDouble(2, ac.getUniqueId());
                pstm.setString(3, ac.getUsername());
                pstm.setString(4, ac.getMyCart().getGameName());
                pstm.setDouble(5, ac.getMyCart().getTotalprice());
                pstm.setDouble(6, ac.getMyMoney());

                pstm.executeUpdate();

            } catch (SQLException ex) {
                ex.getMessage();
            }
            String sql2 = "UPDATE CUSTOMERACCOUNT set mymoney=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
            try (Statement stm = con.createStatement();) {
                stm.executeUpdate(sql2);
                System.out.println("ชำระเงินเสร็จสมบูรณ์ โปรดตรวจสอบ Library ของคุณหลังชำระเงิน ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
