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
    private static Scanner sc = new Scanner(System.in);
    public static void login(){
		while (true) {
			String ep;
			while (true){
				System.out.printf("Email/phone : ");
				ep = sc.nextLine();
				if (validEmail(ep) == true || validPhone(ep) == true){
					break;
				}
			}
			System.out.printf("Password : ");
			String pass = password();
			pass = md5(pass);
			List<Customer> listCustomer = CustomerDal.getAll();
			for(int i = 0; i < listCustomer.size(); i++){
				if((ep.equalsIgnoreCase(listCustomer.get(i).getEmail())
				|| ep.equalsIgnoreCase(listCustomer.get(i).getPhone()))
				&& pass.equalsIgnoreCase(listCustomer.get(i).getPassword())){
					System.out.println("Đăng nhập thành công");
					sc.nextLine();
					menuCustomer();
				}
				else{
					System.out.print("Email/phone hoặc password không đúng\nBạn có muốn nhập lại (C/K) :");
					String ck = BookBl.yesOrNo();
					if (ck.equalsIgnoreCase("k")) {
						break;
					}
				}
			}
		}
	
    }

//   public static boolean login(){
// 	while (true) {
// 		String ep;
// 		while (true){
// 			System.out.printf("Email/phone : ");
// 			ep = sc.nextLine();
// 			if (validEmail(ep) == true || validPhone(ep) == true){
// 				break;
// 			}
// 		}
// 		System.out.printf("Password : ");
// 		String pass = password();
// 		pass = md5(pass);
// 		if(CustomerDal.login(ep, pass) == true){
// 			System.out.println("Đăng nhập thành công");
// 			sc.nextLine();
// 			menuCustomer();
// 		}
// 		else{
// 			System.out.print("Email/phone hoặc password không đúng\nBạn có muốn nhập lại (C/K) :");
// 			String ck = BookBl.yesOrNo();
// 			if (ck.equalsIgnoreCase("k")) {
// 				break;
// 			}
// 		}
// 	}
//     return true;
//   }

  public static void menuCustomer(){
	while(true){
        String[] menuMain = {"Sản phẩm", "Quản lý tài khoản", "Quản lý hóa đơn", "Thoát"};
        int choose = App.menu(menuMain, "Chào mừng bạn đến với Bookstore");
        switch (choose) {
                case 1:
                    
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
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$.*%^&+=]{0,})(?=\\S+$).{8,}";
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