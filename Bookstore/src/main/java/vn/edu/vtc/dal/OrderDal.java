package vn.edu.vtc.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.persistance.Order;

public class OrderDal {
  public Order getOrder(final ResultSet rs) {
    final Order order = new Order();
    try {
      order.setOrderId(rs.getInt("order_id"));
      order.setOrderStatus(rs.getString("order_status"));
      order.setDateTime(rs.getDate("date_time"));
      order.setPaymentMethod(rs.getString("payment_method_name"));
      order.setShippingUnit(rs.getString("shipping_unit_name"));
      order.setShippingFee(rs.getDouble("shippingFee"));
      order.setAddress(rs.getInt("address_id"));
    } catch (final Exception e) {
      // TODO: handle exception
    }
    return order;
  }

  public Order detailOrder(final int id) {
    Order order = new Order();
    try {
      final String sql = "{call detail_order(?)}";
      final Connection con = DbUtil.getConnection();
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, id);
      callableStatement.executeUpdate();
      final ResultSet rs = callableStatement.executeQuery();
      while (rs.next()) {
        order = getOrder(rs);
      }
      con.close();
    } catch (final Exception e) {
    }
    return order;
  }

  public String searchDefaultAddressId(final int id) {
    try {
      final Connection con = DbUtil.getConnection();
      final String sql = "{call search_address_id(?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, id);
      callableStatement.executeUpdate();
      final ResultSet rs = callableStatement.executeQuery();
      while (rs.next()) {
        String a = "Dia chi mac dinh\n" + "Ma dia chi     : " + rs.getInt(1) + "\nTen nguoi nhan : " + rs.getString(2)
            + "\nSo dien thoai  : " + rs.getString(3) + "\nDia chi        : " + rs.getString(6) + " , "
            + rs.getString(5) + " , " + rs.getString(4) + "\n=================================================";
        con.close();
        return a;
      }
    } catch (final SQLException e) {
      e.getSQLState();
      return null;
    }
    return null;
  }

  public List<Order> getAll() {
    List<Order> listOrder = new ArrayList<>();
    try {
      Connection con = DbUtil.getConnection();
      String se = "{call display_order}";
      CallableStatement d = con.prepareCall(se);
      ResultSet rs = d.executeQuery();
      while (rs.next()) {
        listOrder.add(getOrder(rs));
      }
    } catch (SQLException ex) {
    }
    return listOrder;
  }

  public boolean orderExists(int idCustomer, int id) {
    int count = 0;
    try {
        final String sql = "{call order_exists(?,?)}";
        final Connection con = DbUtil.getConnection();
        final CallableStatement callableStatement = con.prepareCall(sql);
        callableStatement.setInt(1, idCustomer);
        callableStatement.setInt(2, id);
        callableStatement.executeUpdate();
        final ResultSet rs = callableStatement.executeQuery();
        while (rs.next()) {
            count++;
        }
        if (count == 0) {
            return false;
        }
    } catch (final Exception e) {
        return false;
    }
    return true;
}
}