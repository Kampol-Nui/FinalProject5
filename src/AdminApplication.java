
import account.AccountStatus;
import dataaccess.DBmanager;
import java.util.Scanner;
import person.Person;
import service.CustomerAccount;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MINI
 */
public class AdminApplication {

    static int choice;
    static String adminMenu = "Menu:\n"
            + "1. list all customer\n"
            + "0. exit\n"
            + "Select menu: ";

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
            adminMenu();
  
    }

    public static void adminMenu() {
        do {
            System.out.print(adminMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    DBmanager.selectAllCustomer();
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }
    
}
