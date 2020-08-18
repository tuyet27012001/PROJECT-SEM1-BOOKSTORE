package vn.edu.vtc.bl;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Customer;

public class CustomerBl {
	private final Scanner sc = new Scanner(System.in);
	final CustomerDal customerDal = new CustomerDal();
	Presentation presentation = new Presentation();

	public boolean login(final String ep, final String pass) {
		boolean log = false;
		if (customerDal.login(ep, pass) == true) {
			System.out.println("Dang nhap thanh cong");
			log = true;
			sc.nextLine();
			return log;
		}
		System.out.print("Email/phone hoac password khong dung\nBan co muon nhap lai (C/K) :");
		final String ck = presentation.yesOrNo();
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
			sc.nextLine();
		}
		else{
			System.out.println("Dang ky that bai");
			sc.nextLine();
		}
		return reg;
	}

	
	

	public String password() {
		final Console console = System.console();
		if (console == null) {
			System.out.println("Couldn't get Console instance");
			System.exit(0);
		}
		final char[] passwordArray = console.readPassword("");
		final String pass = new String(passwordArray);
		return pass;
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