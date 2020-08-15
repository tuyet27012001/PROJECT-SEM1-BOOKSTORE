package vn.edu.vtc.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;

import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.persistance.Customer;

public class CustomerDalTest {
    
    private final CustomerDal customerDal = new CustomerDal();
    private final CustomerBl customerBl = new CustomerBl();

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
}