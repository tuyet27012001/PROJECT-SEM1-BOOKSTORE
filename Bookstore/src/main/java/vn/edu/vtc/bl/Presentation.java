package vn.edu.vtc.bl;

import java.text.DecimalFormat;
import java.util.Scanner;
public class Presentation {
	Scanner sc = new Scanner(System.in);
	public boolean validEmail(final String email) {
		final String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public String dateBirth(String date) {
		int a = date.indexOf('-');
		int b = date.indexOf('-', 3);
		String str1 = date.substring(0, a);
		String str2 = date.substring(a+1, b);
		String str3 = date.substring(b+1, date.length());
		return str3+"-"+str2+"-"+str1;
	}

	public boolean validPassword(final String pass) {
		final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$.*%^&+=]{0,})(?=\\S+$).{8,12}";
		return pass.matches(regex);
	}

	public boolean validPhone(final String phone) {
		final String regex = "^[0-9]{10,11}$";
		return phone.matches(regex);
	}

	public boolean validDate(final String date) {
		final String regex = "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-]([1][8-9][0-9][0-9]|[2][0][01][0-9])$";
		return date.matches(regex);
	}

	public boolean validName(final String name) {
		final String regex = "([A-Za-z ]{0,})";
		return name.matches(regex);
	}

	public String yesOrNo() {
    while (true) {
      String y = sc.nextLine();
      if (y.equalsIgnoreCase("k") || y.equalsIgnoreCase("c")) {
          return y;
      } else {
          System.out.print("Ban nha sai !\nMoi nhap lai : ");
      }
    }
  }


  public Integer validateInteger() {
    int x;
            do {
        try {
            x = Integer.parseInt(sc.nextLine());
            break;

        } catch (Exception e) {
            System.out.print("Sai kieu du lieu!\nMoi nhap lai : ");
        }
    } while (true);
    return x;
  }

  public String format(double d){
    DecimalFormat formatter = new DecimalFormat("###,###,###"); 
    return formatter.format(d);
  }
}