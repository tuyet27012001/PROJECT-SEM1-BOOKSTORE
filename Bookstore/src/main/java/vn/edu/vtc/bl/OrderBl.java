package vn.edu.vtc.bl;

import java.util.List;

import vn.edu.vtc.dal.OrderDal;
import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Order;

public class OrderBl {
  OrderDal orderDal = new OrderDal();

  public Order viewOrderDetails(final int id) {
    return orderDal.viewOrderDetails(id);
  }

  public String searchDefaultAddressId(final int id) {
    return orderDal.searchDefaultAddressId(id);
  }

  public List<Order> getAll(int id) {
    return orderDal.getAll(id);
  }

  public String displayPaymentMethods() {
    return orderDal.displayPaymentMethods();
  }  

  public boolean orderExists(int idCustomer, int id) {
		return orderDal.orderExists(idCustomer, id);
  }
  
  public String displayShippingUnit() {
    return orderDal.displayShippingUnit();
  }

  public boolean insertOrder(String dateTime, int paymentMethod, int shippingUnit, int addressId, String orderStatus) {
    return orderDal.insertOrder(dateTime, paymentMethod, shippingUnit, addressId, orderStatus);
  }

  public int orderId() {
    return orderDal.orderId();
  }

  public int addressId(int idCustomer) {
    return orderDal.addressId(idCustomer);
  }

  public boolean insertBookOrder(int bookId, int orderId, int quantity, double price) {
    return orderDal.insertBookOrder(bookId, orderId, quantity, price);
  }

  public List<Book> orderListBook(int id) {
    return orderDal.orderListBook(id);
  }

}