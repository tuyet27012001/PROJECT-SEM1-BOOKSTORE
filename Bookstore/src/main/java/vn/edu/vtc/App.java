package vn.edu.vtc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Customer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class App {
    static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerBl customerBl = new CustomerBl();
    public static void main(final String[] args) throws SQLException {
        while (true) {
            String[] menuMain = { "Sản phẩm", "Đăng nhập", "Đăng ký", "Thoát" };
            int choose = menu(menuMain, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    BookBl.menuBook();
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

    public static void login() {
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
        pass = md5(pass);

        if (customerBl.login(ep, pass) == true) {
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

    public static void register() {
        final Presentation presentation = new Presentation();
        final CustomerDal customerDal = new CustomerDal();
        try {
            final List<Customer> listCustomer = customerDal.getAll();
            final Customer cus = new Customer();
            System.out.printf("Ten khach hang : ");
            cus.setName(sc.nextLine());
			while (true) {
				System.out.printf("So dien thoai : ");
				final String p = sc.nextLine();
				for (int i = 0; i < 0; i++) {
					if (p.equals(listCustomer.get(i).getPhone())) {
						System.out.printf("So dien thoai da ton tai b co muon dang nhap ?(C/K) : ");
						final String ck = BookBl.yesOrNo();
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
			}
			while (true) {
				System.out.printf("Email : ");
				final String e = sc.nextLine();
				if (presentation.validEmail(e) == true || e == null) {
					cus.setEmail(e);
					break;
				}
			}
			while (true) {
				System.out.println("Gioi tinh : ");
				System.out.println("1. Nam ");
				System.out.println("2. Nu ");
				System.out.printf("Chon : ");
				final int choose = sc.nextInt();
				if (choose == 1) {
					cus.setGender("Nam");
					break;
				}
				else if(choose == 2) {
					cus.setGender("Nu");
					break;
				}
			}
			sc.nextLine();
			System.out.printf("Ngay sinh : ");
			cus.setBirthDate(sc.nextLine());
			while (true) {
				System.out.printf("Mat khau : ");
				final String pass = sc.nextLine();
				System.out.printf("Nhap lai mat khau : ");
				final String pass2 = sc.nextLine();
				if (pass.equals(pass2)) {
					cus.setPassword(md5(pass));
					break;
				} else {
					System.out.printf("Mat khau khong khop vui long nhap lai !\n");
				}
			}
            if(customerBl.register(cus) == true){
                login();
            }
			
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}

    public static String md5(final String str) {
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			final BigInteger bigInteger = new BigInteger(1, digest.digest());
			result = bigInteger.toString(16);
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
