package service;


import dataaccess.DBmanager;
import genarate.GetNextID;
import person.Person;
import account.Account;
import account.AccountStatus;


public class CustomerAccount extends Account {
    
    private long uniqueId;
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
        
    }
    
    private void callKeepCustomerInfo() {
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
            DBmanager.topupMoney(this);
        }
    }
    
    public long getUniqueId() {
        return uniqueId;
    }
    
    public void listBuyingHistory() {
        DBmanager.SelectPurchaseHistory(this);
    }
    
    public GameLibrary getMyLibrary() {
        return myLibrary;
    }
    
}
