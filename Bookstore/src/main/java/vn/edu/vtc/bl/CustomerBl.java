package vn.edu.vtc.bl;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Customer;

public class CustomerBl {
	private Scanner sc = new Scanner(System.in);

	public boolean login(String ep, String pass) {
		CustomerDal customerDal = new CustomerDal();
		boolean log = false;
		if (customerDal.login(ep, pass) == true) {
			System.out.println("Dang nhap thanh cong");
			log = true;
			sc.nextLine();
			return log;
		}
		System.out.print("Email/phone hoac password khong dung\nBan co muon nhap lai (C/K) :");
		final String ck = BookBl.yesOrNo();
		if (ck.equalsIgnoreCase("c")) {
			App.loginApp();
		}
		return log;
	}

	public boolean register() {
		Presentation presentation = new Presentation();
		CustomerDal customerDal = new CustomerDal();

		try {

			List<Customer> listCustomer = customerDal.getAll();
			final Customer cus = new Customer();
			System.out.printf("Ten khach hang : ");
			cus.setName(sc.nextLine());
			while (true) {
				System.out.printf("So dien thoai : ");
				String p = sc.nextLine();
				for (int i = 0; i < 0; i++) {
					if (p.equals(listCustomer.get(i).getPhone())) {
						System.out.printf("So dien thoai da ton tai b co muon dang nhap ?(C/K) : ");
						final String ck = BookBl.yesOrNo();
						if (ck.equalsIgnoreCase("c")) {
							App.loginApp();
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
				String e = sc.nextLine();
				if (presentation.validEmail(e) == true || e == null) {
					cus.setEmail(e);
					break;
				}
			}
			System.out.printf("Gioi tinh : ");
			cus.setGender(sc.nextLine());
			System.out.printf("Ngay sinh : ");
			cus.setBirthDate(sc.nextLine());
			while (true) {
				System.out.printf("Mat khau : ");
				String pass = sc.nextLine();
				System.out.printf("Nhap lai mat khau : ");
				String pass2 = sc.nextLine();
				if (pass.equals(pass2)) {
					cus.setPassword(md5(pass));
					break;
				} else {
					System.out.printf("Mat khau khong khop vui long nhap lai !\n");
				}
			}

			customerDal.insertCustomer(cus);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// public boolean validName(final String name) {
	// final String regex = "[a-zA-Z ]";
	// return name.matches(regex);
	// }

	public void menuCustomer() {
		while (true) {
			final String[] menuMain = { "Sach", "Quản lý tài khoản", "Quản lý hóa đơn", "Thoát" };
			final int choose = App.menu(menuMain, "Chào mừng bạn đến với Bookstore");
			switch (choose) {
				case 1:
					BookBl.menuBook();
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

	public String md5(final String str) {
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

	public String password() {
		final Console console = System.console();
		if (console == null) {
			System.out.println("Couldn't get Console instance");
			System.exit(0);
		}
		final char[] passwordArray = console.readPassword("Password: ");
		final String pass = new String(passwordArray);
		return pass;
	}
}