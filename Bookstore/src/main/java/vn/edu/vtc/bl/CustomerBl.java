package vn.edu.vtc.bl;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import vn.edu.vtc.dal.CustomerDal;

public class CustomerBl {
  private static Scanner sc = new Scanner(System.in);
  public static boolean login(){
	while (true) {
		String ep;
		while (true) {
			System.out.printf("Email/phone : ");
			ep = sc.nextLine();
			if (validEmail(ep) == true ) {
				break;
			}
		}
		System.out.printf("Password : ");
		String pass = password();
		pass = md5(pass);
		if(CustomerDal.login(ep, pass) == true){
			System.out.println("Đăng nhập thành công");
		}
		else{
			System.out.print("Email/phone hoặc password không đúng\nBạn có muốn nhập lại (C/K) :");
			String ck = BookBl.yesOrNo();
			if (ck.equalsIgnoreCase("k")) {
				break;
			}
		}
	}
    return true;
  }

  public static String md5(String str){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean validEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static boolean validPassword(String pass){
		String regex = "^(?=.d)(?=.[a-z])(?=.[A-Z]).{8,50}";
		return pass.matches(regex);
	}

	public static boolean validPhone(String phone) {
		String regex = "^[0-9]{10,11}$";
		return phone.matches(regex);
	}

	public static String password(){
		Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        char[] passwordArray = console.readPassword("Enter your secret password: ");
		String pass = new String (passwordArray);
		return pass;
	}
}