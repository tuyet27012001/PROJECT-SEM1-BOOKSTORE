package vn.edu.vtc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.Presentation;

public class App {
    public static void main(final String[] args) throws SQLException {
        CustomerBl customerBl = new CustomerBl();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] menuMain = { "Sản phẩm", "Đăng nhập", "Đăng ký", "Thoát" };
            int choose = menu(menuMain, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    BookBl.menuBook();
                    break;
                case 2:
                    clrscr();
                    loginApp();
                    break;
                case 3:
                    customerBl.register();
                    break;
                case 4:
                    String name = "Ttuey";
                    final String regex = "\\s[a-zA-Z]";
                    System.out.println(name.matches(regex));
                    sc.nextLine();
                    // System.exit(0);
                    break;
                default:
                    break;
            }

        }

    }

    public static int menu(final String arr[], final String title) {
        int num;
        clrscr();
        System.out.println("=======================================================");
        System.out.printf("||         %-41s ||\n", title);
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("|| %-1d, %-46s ||\n", i + 1, arr[i]);
        }
        System.out.println("=======================================================");
        System.out.printf("#Chon : ");
        while (true) {
            num = BookBl.validateInteger();
            if (num >= 0 && num <= arr.length) {
                break;
            } else {
                System.out.printf("Ban nhap sai !\nXin vui long nhap lại : ");
            }
        }
        return num;
    }

    public static void loginApp() {
        Presentation presentation = new Presentation();
        CustomerBl customerBl = new CustomerBl();
        Scanner sc = new Scanner(System.in);
        String ep;
        while (true) {
            System.out.printf("Email/phone : ");
            ep = sc.nextLine();
            if (presentation.validEmail(ep) == true || presentation.validPhone(ep) == true) {
                break;
            } else {
                System.out.printf("Ban nhap sai vui long nhap lai !\n");
            }
        }
        String pass = customerBl.password();
        pass = customerBl.md5(pass);

        if(customerBl.login(ep, pass)== true){
           customerBl.menuCustomer();
        }
    }

    public static void clrscr() {
        // Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
}