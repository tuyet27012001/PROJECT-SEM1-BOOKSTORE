package vn.edu.vtc.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Order;

public class OrderDalTest {
  OrderDal orderDal = new OrderDal();

  @Test
  public void insertOrderTest1() {
    try {
      boolean result = orderDal.insertOrder("2020-8-10 12:52:03", 1, 1, 1, "Cho xac nhan");
      boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
    public void getAllTest() {
        try {
            List<Order> listOrder = orderDal.getAll(1);
            assertNotNull(listOrder);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void detailOrderTest() {
        try {
            Order order = orderDal.viewOrderDetails(1);
            assertNotNull(order);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void orderListBookTest() {
        try {
            List<Book> listBook = orderDal.orderListBook(1);
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
    
    @Test
    public void displayPaymentMethodsTest() {
        try {
            String a = orderDal.displayPaymentMethods();
            assertNotNull(a);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void displayShippingUnitTest() {
        try {
            String a = orderDal.displayShippingUnit();
            assertNotNull(a);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
  public void insertBookOrderTest() {
    try {
      boolean result = orderDal.insertBookOrder(1, 1, 1, 97000);
      boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
}