/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class AllCustomer {
    private CustomerAccount[] cus;
    private double cusid;
    private String cusname;
    private String cuspassword;
    private double cusmoney;
    public AllCustomer(CustomerAccount[] cus) {
        this.cus = cus;
    }

    public AllCustomer(double readDouble, String readUTF, String readUTF0, double readDouble0) {
        this.cusid = readDouble;
        this.cusname = readUTF;
        this.cuspassword = readUTF0;
        this.cusmoney = readDouble0;
    }

    public CustomerAccount[] getCus() {
        return cus;
    }
    
    public CustomerAccount getCus(int num) {
        return cus[num];
    }

    @Override
    public String toString() {
        //int i = 1;
        String customer = "Customer "+"{" + "CusId = " + cusid + ", Cus_Username = " + cusname + ", Cus_Password = " + cuspassword + ", Cus_Money = " + cusmoney + '}';
//        i++;
//StringBuilder stb = new StringBuilder();
//stb.append("Customer "+i+++"{" + "d=" + d + ", n=" + n + ", p=" + p + ", m=" + m + '}');
//
//        return stb.toString();
          return customer;
    }
    
    
    
}
