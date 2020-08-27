package vn.edu.vtc.bl;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import vn.edu.vtc.dal.CustomerDal;
import vn.edu.vtc.persistance.Customer;

public class CustomerBl {
	CustomerDal customerDal = new CustomerDal();
	Presentation presentation = new Presentation();

	public boolean login(final String ep, final String pass) {
		return customerDal.login(ep, pass);
	}

	public boolean register(Customer customer) {
		return customerDal.insertCustomer(customer);
	}

	public List<Customer> listCustomer() {
		return customerDal.getAll();
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

	public String encodeMd5(final String str) {
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

	public Customer detailCustomer(int id) {
		return customerDal.detailCustomer(id);
	}

	public boolean updateCustomerName(int id, String name) {
		return customerDal.updateCustomerName(id, name);
	}

	public boolean updateCustomerPhone(int id, String phone) {
		return customerDal.updateCustomerPhone(id, phone);
	}

	public boolean updateCustomerEmail(int id, String email) {
		return customerDal.updateCustomerEmail(1, email);
	}

	public boolean updateCustomerGender(int id, String gender) {
		return customerDal.updateCustomerGender(id, gender);
	}

	public boolean updateCustomerBirth(int id, String birth) {
		return customerDal.updateCustomerBirth(id, birth);
	}

	public boolean updateCustomerPass(int id, String pass) {
		return customerDal.updateCustomerPass(id, pass);
	}

	public boolean updateNameAddress(int id, String pass) {
		return customerDal.updateNameAddress(id, pass);
	}

	public boolean updatePhoneAddress(int id, String phone) {
		return customerDal.updatePhoneAddress(id, phone);
	}

	public boolean updateAddress(int id, String city, String district, String address) {
		return customerDal.updateAddress(id, city, district, address);
	}

	public boolean insertAddress(String name, String phone, String city, String district, String address, int id) {
		return customerDal.insertAddress(name, phone, city, district, address, id);
	}

	public String viewAddressList(int id) {
		return customerDal.viewAddressList(id);
	}

	public boolean addressExists(int idCustomer, int id) {
		return customerDal.addressExists(idCustomer, id);
	}

	public boolean updateStatusAddress(final int id) {
		return customerDal.updateStatusAddress(id);
	}

	public boolean updateDefaultAddress(int id, String str) {
		return customerDal.updateDefaultAddress(id, str);
	}

	public String searchDefaultAddress(int id) {
		return customerDal.searchDefaultAddress(id);
	}

	public boolean searchAddress(final int id) {
		return customerDal.searchAddress(id);
	}

	public int searchDefaultAddressId(final int id) {
		return customerDal.searchDefaultAddressId(id);
	}
}