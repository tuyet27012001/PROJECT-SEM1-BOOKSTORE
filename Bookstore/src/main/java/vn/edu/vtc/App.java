package vn.edu.vtc;

import java.io.IOException;
import java.util.Scanner;

import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.pl.BookPl;
import vn.edu.vtc.pl.CustomerPl;
import vn.edu.vtc.pl.OrderPl;

public class App {
    static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerPl customerPl = new CustomerPl();
    private static BookPl bookPl = new BookPl();
    private static OrderPl orderPl = new OrderPl();
    public static int idCustomer = 0;
    public static int idAddress = 0;

    public static void main(final String[] args) {
        final App app = new App();
        app.mainMenu();
    }

    public void mainMenu() {
        final App app = new App();
        while (true) {
            app.clrscr();
            final String[] menuMain = { "Tim kien sach", "Dang nhap", "Dang ky", "Thoat" };
            final int choose = app.menu(menuMain, "Chao mung ban den voi Bookstore");
            switch (choose) {
                case 1:
                    bookPl.menuBook();
                    if (idCustomer != 0) {
                        menuCustomer();
                    }
                    break;
                case 2:
                    if (customerPl.login() == true) {
                        menuCustomer();
                    }
                    break;
                case 3:
                    customerPl.register();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public int menu(final String arr[], final String title) {
        int num;
        if (title.equals("")) {
            System.out.println("=======================================================");
        } else {
            System.out.println("=======================================================");
            System.out.printf("||         %-41s ||\n", title);
            System.out.println("-------------------------------------------------------");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("|| %-1d, %-46s ||\n", i + 1, arr[i]);
        }
        System.out.println("=======================================================");
        System.out.printf("#Chon : ");
        while (true) {
            num = presentation.validateInteger();
            if (num >= 0 && num <= arr.length) {
                break;
            } else {
                System.out.printf("Ban nhap sai !\nXin vui long nhap lại : ");
            }
        }
        return num;
    }

    public void clrscr() {
        // Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public void menuCustomer() {
        final App app = new App();
        while (true) {
            clrscr();
            System.out.println("Tai khoan : " + customerPl.nameCustomer(idCustomer));
            final String[] menuMain = { "Sach", "Quan ly tai khoan", "Quan ly hoa don", "Gio hang", "Thoat" };
            final int choose = app.menu(menuMain, "Chao mung ban den voi Bookstore");
            switch (choose) {
                case 1:
                    bookPl.menuBook();
                    break;
                case 2:
                    customerPl.accountManagement(idCustomer);
                    break;
                case 3:
                    orderPl.viewOrderList();
                    break;
                case 4:
                    try {
                        orderPl.viewCart();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}