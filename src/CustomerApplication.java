
import account.AccountStatus;
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
public class CustomerApplication {

    static int choice;
    static String customerMenu = "Menu:\n"
            + "1. Register\n"
            + "0. exit\n"
            + "Select menu: ";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        customerMenu();
    }

    public static void customerMenu() {
        do {
            System.out.print(customerMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    String phone = null;
                    String email = null;
                    String name = null;
                    String username = null;
                    String password = null;
                    System.out.print("Input your name: ");
                    name = input.next();
                    System.out.print("Input your email: ");
                    email = input.next();
                    System.out.print("Input your phone: ");
                    phone = input.next();
                    System.out.print("Input your username: ");
                    username = input.next();
                    System.out.print("Input your password: ");
                    password = input.next();
                    System.out.println("Registered\n");
                    new CustomerAccount(username, password, AccountStatus.ACTIVE, new Person(name, email, phone));
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }
}
