package vn.edu.vtc.bl;

import java.util.List;

import vn.edu.vtc.dal.OrderDal;
import vn.edu.vtc.persistance.Order;

public class OrderBl {
  OrderDal orderDal = new OrderDal();

  public Order detailOrder(final int id) {
    return orderDal.detailOrder(id);
  }

  public String searchDefaultAddressId(final int id) {
    return orderDal.searchDefaultAddressId(id);
  }

  public List<Order> getAll() {
    return orderDal.getAll();
  }

  

  public boolean orderExists(int idCustomer, int id) {
		return orderDal.orderExists(idCustomer, id);
	}

}