package service;


import game.Game;
import dataaccess.DBmanager;
import java.util.ArrayList;

public class GameLibrary {

    private ArrayList<Game> myGameLibrary;
    private CustomerAccount ac;

    public GameLibrary(CustomerAccount ac) {
        this.ac = ac;
        this.myGameLibrary = new ArrayList<>();
    }

    public void addGameFromCartToLibrary() {

        ac.getMyCart().calculateTotalPrice();
        if (ac.getMyCart().getTotalprice() <= dataaccess.DBmanager.SelectLastMoney(ac)) {
            this.myGameLibrary = (ArrayList<Game>) ac.getMyCart().itemInCart.clone();
            double oldmoney = dataaccess.DBmanager.SelectLastMoney(ac);
            ac.myMoney = dataaccess.DBmanager.SelectLastMoney(ac) - ac.getMyCart().getTotalprice();
            DBmanager.PurchaseGame(ac);
            ac.getMyCart().itemInCart.clear();
            System.out.println("ยอดเงินคงเหลือหลังชำระ : " + ac.getMyMoney() + " ยอดเงินก่อนชำระ : " + oldmoney);
        } else {
            System.out.println("จำนวนเงินในกระเป๋าของคุณไม่เพียงพอ โปรดเติมเงินของคุณ");
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
