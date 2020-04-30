
import dataaccess.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GameLibrary {

    private ArrayList<Game> myGameLibrary;
    private CustomerAccount ac;
    
    public GameLibrary(CustomerAccount ac) {
        this.ac = ac;
        this.myGameLibrary = new ArrayList<>();
    }

    public void addGameFromCartToLibrary() {
        String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(id,name,password,carttotalprice,mymoney)" + "VALUES(?,?,?,?,?)";
        ac.getMyCart().calculateTotalPrice();
        try (Connection con = DBconnection.getConnecting();) {

            if (ac.getMyCart().getTotalprice() <= dataaccess.DBconnection.SelectLastMoney(ac.getUniqueId())) {
                this.myGameLibrary = (ArrayList<Game>) ac.getMyCart().itemInCart.clone();
                double oldmoney = dataaccess.DBconnection.SelectLastMoney(ac.getUniqueId());
                ac.myMoney = dataaccess.DBconnection.SelectLastMoney(ac.getUniqueId()) - ac.getMyCart().getTotalprice();
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
                    System.out.println("ยอดเงินคงเหลือหลังชำระ : " + ac.getMyMoney() + " ยอดเงินก่อนชำระ : " + oldmoney);
                } catch (SQLException ex) {
                    ex.getMessage();
                }

                ac.getMyCart().itemInCart.clear();

            } else {
                System.out.println("จำนวนเงินในกระเป๋าของคุณไม่เพียงพอ โปรดเติมเงินของคุณ");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
    }

    public ArrayList<Game> getMyGameLibrary() {
        try {
            System.out.println("************************ MY Library *************************");
            return this.myGameLibrary;
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
