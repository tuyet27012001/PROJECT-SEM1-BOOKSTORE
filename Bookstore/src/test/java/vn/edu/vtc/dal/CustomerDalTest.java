package vn.edu.vtc.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;

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

    // @Test
    // public void registerTest1() {
    //     try {
    //         Customer cus = new Customer();
    //         cus.setName("tuyet");
    //         cus.setPhone("0934334235");
    //         cus.setEmail("anh@gmail.com");
    //         cus.setGender("Nu");
    //         cus.setPassword(customerBl.encodeMd5("Tuyet235"));
    //         cus.setBirthDate("2001-01-27");
    //         boolean result = customerDal.insertCustomer(cus);
    //         boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }


    @Test
    public void registerTest2() {
        try {
            Customer cus = new Customer();
            cus.setName("tuyet1");
            cus.setPhone("1234123412");
            cus.setEmail("anh1@gmail.com");
            cus.setGender("Nu");
            cus.setPassword(customerBl.encodeMd5("Tuyet123"));
            cus.setBirthDate("2001-03-27");
            boolean result = customerDal.insertCustomer(cus);
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    // @Test
    // public void registerTest3() {
    //     try {
    //         Customer cus = new Customer();
    //         cus.setName("tuyet2");
    //         cus.setPhone("0234123412");
    //         cus.setEmail("mlqaqqgz@gmail.com");
    //         cus.setGender("Nu");
    //         cus.setPassword(customerBl.encodeMd5("Dkfdksf123"));
    //         cus.setBirthDate("2001-02-27");
    //         boolean result = customerDal.insertCustomer(cus);
    //         boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }

    // @Test
    // public void registerTest4() {
    //     try {
    //         Customer cus = new Customer();
    //         cus.setName("tuyet3");
    //         cus.setPhone("0124123413");
    //         cus.setEmail("kienham@gmail.com");
    //         cus.setGender("Nu");
    //         cus.setPassword(customerBl.encodeMd5("Kiencho1"));
    //         cus.setBirthDate("2001-04-27");
    //         boolean result = customerDal.insertCustomer(cus);
    //         boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }

    // @Test
    // public void login1() {
    //     try {
    //         String pass = "Tuyet235";
    //         pass = customerBl.encodeMd5(pass);
    //         final boolean result = customerDal.login("anh@gmail.com", pass);
    //         final boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }

    // @Test
    // public void login2() {
        
    //     try {
    //         String pass = "Tuyet123";
    //         pass = customerBl.encodeMd5(pass);
    //         final boolean result = customerDal.login("1234123412", pass);
    //         final boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }

    // @Test
    // public void login3() {
        
    //     try {
    //         String pass = "Dkfdksf123";
    //         pass = customerBl.encodeMd5(pass);
    //         final boolean result = customerDal.login("anhtuyetnjnjljl@gmail.com", pass);
    //         final boolean expected = false;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }

    // @Test
    // public void login4() {
    //     try {
    //         String pass = "Kiencho1";
    //         pass = customerBl.encodeMd5(pass);
    //         final boolean result = customerDal.login("kienham@gmail.com", pass);
    //         final boolean expected = true;
    //         assertEquals(expected, result);
    //     } catch (final Exception e) {
    //         // TODO: handle exception
    //     }
    // }



    @Test
    public void insertAddressTest1() {
        try {
            final boolean result = customerDal.insertAddress("tuyet", "0934334235","Ha Noi", "Hoai Duc", "Thon 3, Cat Que", 1);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void insertAddressTest2() {
        try {
            final boolean result = customerDal.insertAddress("tuyet1", "1234123412","Ha Noi", "Hoai Duc", "Thon 4, Cat Que", 1);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void insertAddressTest3() {
        try {
            final boolean result = customerDal.insertAddress("tuyet2", "0234123412","Thanh Hoa", "Bim Son", "Khu 7, Ba Dinh", 1);
            final boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void insertAddressTest4() {
        try {
            final boolean result = customerDal.insertAddress("tuyet3", "0234123413","Thanh Hoa", "Bim Son", "Khu 7, Ba Dinh", 1);
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
    public void detailCustomerTest() {
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
    public void updateCustomerNameTest2() {
        try {
            boolean result = customerDal.updateCustomerName(1, "Long");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }


    @Test
    public void updateCustomerNameTest3() {
        try {
            boolean result = customerDal.updateCustomerName(1, "Hue");
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
    public void updateCustomerPhoneTest2() {
        try {
            boolean result = customerDal.updateCustomerPhone(1, "0934343244");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPhoneTest3() {
        try {
            boolean result = customerDal.updateCustomerPhone(1, "0923343434");
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
    public void updateCustomerEmailTest2() {
        try {
            boolean result = customerDal.updateCustomerEmail(1, "tuyetxinhgai@gmail.com");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerEmailTest3() {
        try {
            boolean result = customerDal.updateCustomerEmail(1, "tuyetxinh@gmail.com");
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
    public void updateCustomerGenderTest2() {
        try {
            boolean result = customerDal.updateCustomerGender(1, "Nu");
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
    public void updateCustomerBirthTest2() {
        try {
            boolean result = customerDal.updateCustomerBirth(1, "2001-02-23");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerBirthTest3() {
        try {
            boolean result = customerDal.updateCustomerBirth(1, "1999-12-03");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPasswordTest1() {
        try {
            boolean result = customerDal.updateCustomerPass(1, customerBl.encodeMd5("Tuyet2002"));
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPasswordTest2() {
        try {
            boolean result = customerDal.updateCustomerPass(1, customerBl.encodeMd5("Kien2004"));
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateCustomerPasswordTest3() {
        try {
            boolean result = customerDal.updateCustomerPass(1, customerBl.encodeMd5("Long2002"));
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
    public void updateNameAddressTest2() {
        try {
            boolean result = customerDal.updateNameAddress(1, "Long");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updateNameAddressTest3() {
        try {
            boolean result = customerDal.updateNameAddress(1, "Kien cho");
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
    public void updatePhoneAddressTest2() {
        try {
            boolean result = customerDal.updatePhoneAddress(1, "0284384834");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void updatePhoneAddressTest3() {
        try {
            boolean result = customerDal.updatePhoneAddress(1, "0934324343");
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
    public void updateAddressTest2() {
        try {
            boolean result = customerDal.updateAddress(2,"Hai phong", "Lang Ong", "So nha 23");
            boolean expected = true;
            assertEquals(expected, result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void displayAddressTest1() {
        try {
            String result = customerDal.viewAddressList(1);
            assertNotNull(result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
    
}