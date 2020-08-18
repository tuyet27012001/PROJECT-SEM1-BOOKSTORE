package vn.edu.vtc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Customer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {
    static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerBl customerBl = new CustomerBl();
    private static BookBl bookBl = new BookBl();

    public static void main(final String[] args) throws SQLException {
        while (true) {
            String[] menuMain = { "Sản phẩm", "Đăng nhập", "Đăng ký", "Thoát" };
            int choose = menu(menuMain, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    menuBook();
                    break;
                case 2:
                    clrscr();
                    login();
                    break;
                case 3:
                    clrscr();
                    register();
                    break;
                case 4:
                    String name = "fruuew vTGS";
                    String regex = "([A-Za-z ]{0,})";
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
            num = presentation.validateInteger();
            if (num >= 0 && num <= arr.length) {
                break;
            } else {
                System.out.printf("Ban nhap sai !\nXin vui long nhap lại : ");
            }
        }
        return num;
    }

    public static void login() {
        String ep;
        while (true) {
            System.out.printf("Email/So dien thoai : ");
            ep = sc.nextLine();
            if (presentation.validEmail(ep) == true || presentation.validPhone(ep) == true) {
                break;
            } else {
                System.out.printf("Ban nhap sai vui long nhap lai !\n");
            }
        }
        System.out.printf("Mat khau : ");
        String pass = customerBl.password();
        pass = customerBl.md5(pass);

        if (customerBl.login(ep, pass) == true) {
            menuCustomer();
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

    public static void register() {
        final Presentation presentation = new Presentation();
        final CustomerDal customerDal = new CustomerDal();
        try {
            final List<Customer> listCustomer = customerDal.getAll();
            final Customer cus = new Customer();
            while (true) {
                System.out.printf("Ten khach hang : ");
                String n = sc.nextLine();
                n = n.trim();
                if (presentation.validName(n) == true && n.isEmpty() == false) {
                    cus.setName(n);
                    break;
                }
                else{
                    System.out.println("Ban nhap sai !\nXin vui long nhap lai.");
                }
            }
            while (true) {
                System.out.printf("So dien thoai : ");
                String p = sc.nextLine();
                p = p.trim();
                for (int i = 0; i < 0; i++) {
                    if (p.equals(listCustomer.get(i).getPhone())) {
                        System.out.printf("So dien thoai da ton tai b co muon dang nhap ?(C/K) : ");
                        final String ck = presentation.yesOrNo();
                        if (ck.equalsIgnoreCase("c")) {
                            login();
                        } else
                            break;
                    }
                }
                if (presentation.validPhone(p) == true || p == null) {
                    cus.setPhone(p);
                    break;
                }
                else{
                    System.out.println("Ban nhap sai !Xin vui long nhap lai.");
                }
            }
            while (true) {
                System.out.printf("Email : ");
                String e = sc.nextLine();
                e = e.trim();
                for (int i = 0; i < 0; i++) {
                    if (e.equals(listCustomer.get(i).getEmail())) {
                        System.out.printf("Email da ton tai b co muon dang nhap ?(C/K) : ");
                        final String ck = presentation.yesOrNo();
                        if (ck.equalsIgnoreCase("c")) {
                            login();
                        } else
                            break;
                    }
                }
                if (presentation.validEmail(e) == true || e == null) {
                    cus.setEmail(e);
                    break;
                }
                else{
                    System.out.println("Ban nhap sai !Xin vui long nhap lai.");
                }
            }
            while (true) {
                System.out.println("Gioi tinh : ");
                System.out.println("1. Nam ");
                System.out.println("2. Nu ");
                System.out.printf("Chon : ");
                int choose = presentation.validateInteger();
                if (choose == 1) {
                    cus.setGender("Nam");
                    break;
                } else if (choose == 2) {
                    cus.setGender("Nu");
                    break;
                }
                else{
                    System.out.println("Ban nhap sai !\nXin vui long nhap lai!");
                }
            }
            while (true) {
                System.out.printf("Ngay sinh (dd-mm-yyyy): ");
                String date = sc.nextLine();
                date = date.trim();
                if (presentation.validDate(date) == true) {
                    date = presentation.dateBirth(date);
                    cus.setBirthDate(date);
                    break;
                } else {
                    System.out.println("Ban nhap sai , xin vui long nhap lai !");
                }
            }

            while (true) {
                String pass;
                while (true) {
                    System.out.printf("Mat khau : ");
                    pass = customerBl.password();
                    if (presentation.validPassword(pass) == true) {
                        break;
                    } else {
                        System.out.println(
                                "Mat khau khong hop le !\nMat khau phai chua it nhat mot chu cai viet hoa, mot chu cai viet thuong , mot chu so , tu 8-12 ky tu !");
                    }
                }
                System.out.printf("Nhap lai mat khau : ");
                final String pass2 = customerBl.password();
                if (pass.equals(pass2)) {
                    cus.setPassword(customerBl.md5(pass));
                    break;
                } else {
                    System.out.printf("Mat khau khong khop vui long nhap lai !\n");
                }
            }
            if (customerBl.register(cus) == true) {
                login();
            }

        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    public static void menuCustomer() {
		while (true) {
			final String[] menuMain = { "Sach", "Quản lý tài khoản", "Quản lý hóa đơn", "Thoát" };
			final int choose = App.menu(menuMain, "Chào mừng bạn đến với Bookstore");
			switch (choose) {
				case 1:
					menuBook();
					break;
				case 2:
					// clrscr();
					// CustomerBl.login();
					break;
				case 3:

					break;
				case 4:
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}

    public static void menuBook(){
        while(true){
        String[] arr = {"Sach", "Danh muc","Tim kiem", "Thoat"};
        int choose = App.menu(arr, "Chào mừng bạn đến với Bookstore");
        switch (choose) {
          case 1:
              displayBook();
              break;
          case 2:
            //   displayCategory();
              break;
          case 3:
          
              break;
          case 4:
              return;
              
          default:
              break;
          }
      }
    }
  
    public static void displayBook(){
      List<Book> listBook = bookBl.displayBook();
      System.out.println("=============================================================================================================");
      System.out.printf("|%-4s|%-50s|%-30s|%-20s| \n", "Id", "Ten sach", "Tac gia", "Gia (vnđ)");
      System.out.println("=============================================================================================================");
      for(Book rs : listBook){
          System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", rs.getBookId(), rs.getTitle(), rs.getAuthor(),rs.getPrice());
      }
      System.out.println("=============================================================================================================");
      sc.nextLine();
    }
}
