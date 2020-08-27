package vn.edu.vtc.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import vn.edu.vtc.persistance.Order;

public class OrderDalTest {
  OrderDal orderDal = new OrderDal();

  @Test
  public void insertOrderTest() {
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
            Order order = orderDal.detailOrder(1);
            assertNotNull(order);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
}