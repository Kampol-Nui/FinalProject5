
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MINI
 */
public class Application {

    static String menu = "Menu:\n"
            + "1. list all customer\n"
            + "2. add new customer\n"
            + "3. list all product\n"
            + "4. add product to wishlist\n"
            + "5. remove product from wishlist\n"
            + "6. show wishlist\n"
            + "0. exit\n"
            + "Select menu: ";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int select = 0;
        do {
            switch (select) {
                case 1: //select customer
                    listClustomer();
                    break;
                case 2: //list all product
                    addCustomer();
                    break;
                case 3: //add product
                    listProduct();
                    break;
                case 4: //remove

                    break;
                case 5://show wishlist
            }
        } while (select != 0);
        System.out.println("EXIT");
    }
}
