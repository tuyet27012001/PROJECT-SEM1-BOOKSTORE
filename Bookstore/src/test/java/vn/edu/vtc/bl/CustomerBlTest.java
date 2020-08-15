package vn.edu.vtc.bl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerBlTest {
    @Test
    public void login1() {
        try {
            CustomerBl customerBl = new CustomerBl();
            String pass = "tuyet";
            String email = "anh@gmail.com";
            pass = customerBl.md5(pass);
            final boolean result = customerBl.login(email, pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login2() {
        CustomerBl customerBl = new CustomerBl();
        try {
            String pass = "tuyet";
            pass = customerBl.md5(pass);
            final boolean result = customerBl.login("1231231231", pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login3() {
        CustomerBl customerBl = new CustomerBl();
        try {
            String pass = "dkfdksf";
            pass = customerBl.md5(pass);
            final boolean result = customerBl.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = false;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void login4() {
        CustomerBl customerBl = new CustomerBl();
        try {
            String pass = "kiencho";
            pass = customerBl.md5(pass);
            final boolean result = customerBl.login("kienham@gmail.com", pass);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

}