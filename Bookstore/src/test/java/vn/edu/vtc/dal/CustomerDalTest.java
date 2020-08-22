package vn.edu.vtc.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;

import vn.edu.vtc.App;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.persistance.Customer;

public class CustomerDalTest {
    
    private final CustomerDal customerDal = new CustomerDal();
    private final  CustomerBl customerBl = new CustomerBl();
    @Test
    public  void getAllDalTest(){
        final CustomerDal customerDal = new CustomerDal();
        try {
            final List<Customer> listCustomer = customerDal.getAll();
            assertNotNull(listCustomer);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login2() {
        
        try {
            String pass = "tuyet";
            pass = customerBl.md5(pass);
            final boolean result = customerDal.login("1234123412", pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login3() {
        
        try {
            String pass = "dkfdksf";
            pass = customerBl.md5(pass);
            final boolean result = customerDal.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = false;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login4() {
        try {
            String pass = "kiencho";
            pass = customerBl.md5(pass);
            final boolean result = customerDal.login("kienham@gmail.com", pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login1() {
        try {
            String pass = "tuyet";
            pass = customerBl.md5(pass);
            final boolean result = customerDal.login("anh@gmail.com", pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void registerTest1() {
        try {
            Customer cus = new Customer();
            cus.setName("name");
            cus.setPhone("0934334235");
            cus.setEmail("anhtuyet2@gmail.com");
            cus.setGender("Nu");
            cus.setPassword(customerBl.md5("andhsR@12"));
            cus.setBirthDate("2001-01-27");
            boolean result = customerDal.insertCustomer(cus);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void insertAddressTest1() {
        try {
            final boolean result = customerDal.insertAddress("tuyet", "1232323234","Ha noi", "Hoai duc", "Thon 3, Cat Que", 1);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void getAllCustomerTest1(){
        try {
            final List<Customer> listBook = customerDal.getAll();
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void detailCustomerTest1() {
        try {
            Customer customer = customerDal.detailCustomer(1);
            assertNotNull(customer);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerNameTest1() {
        try {
            boolean result = customerDal.updateCustomerName(1, "Tuyet");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPhoneTest1() {
        try {
            boolean result = customerDal.updateCustomerPhone(1, "0932232343");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerEmailTest1() {
        try {
            boolean result = customerDal.updateCustomerEmail(1, "asdsd@gmail.com");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerGenderTest1() {
        try {
            boolean result = customerDal.updateCustomerGender(1, "Nam");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerBirthTest1() {
        try {
            boolean result = customerDal.updateCustomerBirth(1, "2000-12-23");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPasswordTest1() {
        try {
            boolean result = customerDal.updateCustomerPass(1, "Tuyet2002");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateNameAddressTest1() {
        try {
            boolean result = customerDal.updateNameAddress(1, "Kien");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updatePhoneAddressTest1() {
        try {
            boolean result = customerDal.updatePhoneAddress(1, "1231231231");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateDefaultAddressTest1() {
        try {
            boolean result = customerDal.updateDefaultAddress(1, "Mac dinh");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateStaterAddressTest1() {
        try {
            boolean result = customerDal.updateStatusAddress(1);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    

    @Test
    public void searchAddressTest1() {
        try {
            boolean result = customerDal.searchAddress(1);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateAddressTest1() {
        try {
            boolean result = customerDal.updateAddress(1,"Thanh Hoa", "Bim Sua", "Kien Cho");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void displayAddressTest1() {
        try {
            boolean result = customerDal.displayAddress(1);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    // @Test
    // public void addressExistsTest1() {
    //     try {
    //         boolean result = customerDal.addressExists(1, 1);
    //         boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }
    
    @Test
    public void searchDefaultAddressTest1() {
        try {
            boolean result = customerDal.searchDefaultAddress(1);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
}