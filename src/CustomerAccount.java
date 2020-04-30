
import dataaccess.DBconnection;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MINI
 */
public class CustomerAccount extends Account {

    private double uniqueId;
    private Cart myCart;
    private GameLibrary myLibrary;
    private double topupMoney = 0;
    protected double myMoney;
    

    public CustomerAccount(String username, String password, AccountStatus status, Person person) {
        super(username, password, person);
        this.myCart = new Cart();
        this.myLibrary = new GameLibrary(this);
        this.uniqueId = GetNextID.getNext();
        callKeepCustomerInfo();
//        String sql1 = "INSERT INTO CUSTOMERACCOUNT2 " + "(id,username,password,mymoney)" + "VALUES(?,?,?,?)";
//        try (Connection conn = DBconnection.getConnecting();) {
//            try (PreparedStatement pstm = conn.prepareStatement(sql1);) {
//                pstm.setDouble(1, uniqueId);
//                pstm.setString(2, username);
//                pstm.setString(3, password);
//                pstm.setDouble(4, this.myMoney);
//                pstm.executeUpdate();
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
       // WriteCustomerData();
    }
    private void callKeepCustomerInfo(){
        DBmanager.keepCustomerInfo(this);
    }

    public double getMyMoney() {
        return this.myMoney = dataaccess.DBconnection.SelectLastMoney(getUniqueId());
    }

    public double getTopupMoney() {
        return topupMoney;
    }

    public Cart getMyCart() {
        return this.myCart;
    }

    public void TopupMoney(double topupmoney) {
        this.topupMoney = topupmoney;
        if (this.topupMoney <= 0) {
            System.out.println("Please insert your money");

        } else {
            this.myMoney = this.topupMoney + dataaccess.DBconnection.SelectLastMoney(getUniqueId());
            DBmanager.Topupmoney(this);
//            String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(id,name,password,carttotalprice,mymoney,topupmoney)" + "VALUES(?,?,?,?,?,?)";
//            
//            try (Connection con = DBconnection.getConnecting();) {
//                try (
//                        PreparedStatement stm = con.prepareStatement(sql1);) {
//                    stm.setDouble(6, topupmoney);
//                    stm.setDouble(1, this.getUniqueId());
//                    stm.setString(2, this.getUsername());
//                    stm.setDouble(5, this.myMoney);
//                    stm.setString(3, this.getPassword());
//                    stm.setDouble(4, this.myCart.getTotalprice());
//                    stm.executeUpdate();
//
//                } catch (SQLException ex) {
//                    ex.getMessage();
//                }
//            } catch (SQLException ex) {
//                ex.getMessage();
//            }
            //WriteCustomerData();
        }
    }

    public double getUniqueId() {
        return uniqueId;
    }

    public void listBuyingHistory() {
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            String query = ("SELECT * FROM CUSTOMERACCOUNT WHERE ID =" + this.uniqueId);
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
                double topupMoney = rs.getDouble("TOPUPMONEY");
                System.out.println(String.format("%11s %s %10s %s %20s %s %20s %s %14s %s %8s %s %8s ", orderNumber, "|", id, "|", name, "|", password, "|", cartTotalprice, "|", mymoney, "|", topupMoney));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }

    public GameLibrary getMyLibrary() {
        return myLibrary;
    }
    
        

}
