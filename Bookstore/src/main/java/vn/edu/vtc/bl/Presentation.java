package vn.edu.vtc.bl;

public class Presentation {
  public boolean validEmail(final String email) {
		final String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public boolean validPassword(final String pass) {
		final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$.*%^&+=]{0,})(?=\\S+$).{8,}";
		return pass.matches(regex);
	}

	public boolean validPhone(final String phone) {
		final String regex = "^[0-9]{10,11}$";
		return phone.matches(regex);
	}

}