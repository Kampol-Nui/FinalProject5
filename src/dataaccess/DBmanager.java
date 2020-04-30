package dataaccess;


import genarate.TimeStamp;
import dataaccess.DBconnection;
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
        String sql1 = "INSERT INTO CUSTOMERACCOUNT2 " + "(id,username,password,mymoney)" + "VALUES(?,?,?,?)";
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
        String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(timestamp,username,topup,topupstatus)" + "VALUES(?,?,?,?)";

        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement stm = con.prepareStatement(sql1);) {
                stm.setString(1, TimeStamp.getFormattedDate());
                stm.setString(2, ac.getUsername());
                stm.setDouble(3, ac.getTopupMoney());
                stm.setObject(4, TopupStatus.SUCCESSFUL);
                stm.executeUpdate();

            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void SelectBuyingHistory(CustomerAccount ac) {
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            String query = ("SELECT * FROM CUSTOMERACCOUNT WHERE ID =" + ac.getUniqueId());
            rs = stm.executeQuery(query);
            System.out.println("================================================================================================================");
            System.out.println(String.format("%11s %s %10s %s %20s %s %20s %s %14s %s %8s %s %8s ", "ORDERNUMBER", "|", "ID", "|", "NAME", "|", "PASSWORD", "|", "CARTTOTALPRICE", "|", "MYMONEY", "|", "TOPUPMONEY"));
            System.out.println("================================================================================================================");
            while (rs.next()) {
                int orderNumber = rs.getInt("ORDER_NUMBER");
                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String password = rs.getString("PASSWORD");
                double cartTotalprice = rs.getDouble("CARTTOTALPRICE");
                double mymoney = rs.getDouble("MYMONEY");
                double topupMoney1 = rs.getDouble("TOPUPMONEY");
                System.out.println(String.format("%11s %s %10s %s %20s %s %20s %s %14s %s %8s %s %8s ", orderNumber, "|", id, "|", name, "|", password, "|", cartTotalprice, "|", mymoney, "|", topupMoney1));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }

    public static void StoreGame(CustomerAccount ac) {
        String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(id,name,password,carttotalprice,mymoney)" + "VALUES(?,?,?,?,?)";
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement pstm = con.prepareStatement(sql1);) {

                pstm.setDouble(1, ac.getUniqueId());
                pstm.setString(2, ac.getUsername());
                pstm.setDouble(5, ac.getMyMoney());
                pstm.setString(3, ac.getPassword());
                pstm.setDouble(4, ac.getMyCart().getTotalprice());
                pstm.executeUpdate();

            } catch (SQLException ex) {
                ex.getMessage();
            }
            String sql2 = "UPDATE CUSTOMERACCOUNT2 set mymoney=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
            try (Statement stm = con.createStatement();) {
                stm.executeUpdate(sql2);
                System.out.println("ชำระเงินเสร็จสมบูรณ์ โปรดตรวจสอบ Library ของคุณหลังชำระเงิน ");
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
