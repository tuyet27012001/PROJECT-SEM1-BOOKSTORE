package vn.edu.vtc.dal;

public class CustomerDalTest{
    public static void login1(){
        try {
            String pass = "dkfdksf";
            pass = CustomerBl.md5(pass);
            final boolean result = CustomerDal.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = true;
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    public static void login2(){
        try {
            String pass = "dkfdksf";
            pass = CustomerBl.md5(pass);
            final boolean result = CustomerDal.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = true;
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    public static void login3(){
        try {
            String pass = "dkfdksf";
            pass = CustomerBl.md5(pass);
            final boolean result = CustomerDal.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = true;
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    public static void login4(){
        try {
            String pass = "dkfdksf";
            pass = CustomerBl.md5(pass);
            final boolean result = CustomerDal.login("anhtuyetnjnjljl@gmail.com", pass);
            final boolean expected = true;
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }


}