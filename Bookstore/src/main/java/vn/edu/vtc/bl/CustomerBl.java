package vn.edu.vtc.bl;

import java.io.Console;

import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Customer;

public class CustomerBl {
	private final Scanner sc = new Scanner(System.in);
	final CustomerDal customerDal = new CustomerDal();

	public boolean login(final String ep, final String pass) {
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
			App.login();
		}
		return log;
	}

	public boolean register(Customer customer) {
		boolean reg = false;
		if (customerDal.insertCustomer(customer) == true) {
			System.out.println("Dang ky thanh cong.");
			reg = true;
		}
		else{
			System.out.println("Dang ky that bai");
		}
		return reg;
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