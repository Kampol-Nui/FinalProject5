

import dataaccess.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public static void Topupmoney(CustomerAccount ac){
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
}
