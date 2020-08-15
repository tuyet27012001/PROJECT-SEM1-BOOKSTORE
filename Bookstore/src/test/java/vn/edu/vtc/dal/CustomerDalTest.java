package vn.edu.vtc.dal;

import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;
import vn.edu.vtc.persistance.Customer;

public class CustomerDalTest {
    @Test
    public  void getAllDalTest(){
        CustomerDal customerDal = new CustomerDal();
        try {
          List<Customer> listCustomer = customerDal.getAll();
          assertNotNull(listCustomer);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
}