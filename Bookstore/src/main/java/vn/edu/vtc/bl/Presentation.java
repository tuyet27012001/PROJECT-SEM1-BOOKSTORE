package vn.edu.vtc.bl;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

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
		String str2 = date.substring(a + 1, b);
		String str3 = date.substring(b + 1, date.length());
		return str3 + "-" + str2 + "-" + str1;
	}

	public String dateBirth1(String date) {
		int a = date.indexOf('-');
		int b = date.indexOf('-', 5);
		String str1 = date.substring(0, a);
		String str2 = date.substring(a + 1, b);
		String str3 = date.substring(b + 1, date.length());
		return str3 + "-" + str2 + "-" + str1;
	}

	public String dateTime(String date) {
		int a = date.indexOf('-');
		int b = date.indexOf('-', 5);
		String str1 = date.substring(0, a);
		String str2 = date.substring(a + 1, b);
		String str3 = date.substring(b + 1, b + 3);
		String str4 = date.substring(b + 4, date.length());
		return str4 + " " + str3 + "-" + str2 + "-" + str1;
	}

	public boolean validPassword(final String pass) {
		final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$.*%^&+=]{0,})(?=\\S+$).{8,12}";
		return pass.matches(regex);
	}

	public boolean validPhone(final String phone) {
		final String regex = "^([+]{0,})[0-9]{10,12}$";
		return phone.matches(regex);
	}

	// public boolean validDate(final String date) {
	// final String regex =
	// "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-]([1][8-9][0-9][0-9]|[2][0][01][0-9])$";
	// int a = date.indexOf('-');
	// int b = date.indexOf('-', 3);
	// String str1 = date.substring(0, a);
	// String str2 = date.substring(a + 1, b);
	// String str3 = date.substring(b + 1, date.length());
	// int year = Integer.parseInt(str3);
	// boolean check = false;
	// if (year % 4 == 0) {
	// if (year % 100 == 0) {
	// if (year % 400 == 0) {
	// System.out.println("Năm " + year + " là năm nhuận.");
	// if(str2.equals("2") && (str1.equals("30") || str1.equals("31"))){
	// check = false;
	// }
	// else if(str2.equals("2") && (str1.equals("30") || str1.equals("31")))
	// else{

	// }
	// } else {
	// System.out.println("Năm " + year + " không phải là năm nhuận.");
	// }
	// } else {
	// System.out.println("Năm " + year + " là năm nhuận.");
	// }
	// } else {
	// System.out.println("Năm " + year + " không phải là năm nhuận.");
	// }
	// return date.matches(regex);
	// }
	private Pattern pattern;

	private Matcher matcher;

	private static final String DATE_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-]([1][8-9][0-9][0-9]|[2][0][01][0-9])$";

	public boolean validDate(final String date) {
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);

		if (matcher.matches()) {

			matcher.reset();

			if (matcher.find()) {

				String day = matcher.group(1);

				String month = matcher.group(2);

				int year = Integer.parseInt(matcher.group(3));

				if (day.equals("31")

						&& (month.equals("4") || month.equals("6") || month.equals("9")

								|| month.equals("11") || month.equals("04") || month.equals("06")

								|| month.equals("09"))) {

					return false; // only 1,3,5,7,8,10,12 has 31 days

				} else if (month.equals("2") || month.equals("02")) {

					// leap year

					if (year % 4 == 0) {

						if (day.equals("30") || day.equals("31")) {

							return false;

						} else {

							return true;

						}

					} else if (day.equals("29") || day.equals("30") || day.equals("31")) {

						return false;

					} else {

						return true;

					}

				} else {

					return true;

				}

			} else {

				return false;

			}

		} else {

			return false;

		}
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

	public String format(double d) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(d);
	}
}