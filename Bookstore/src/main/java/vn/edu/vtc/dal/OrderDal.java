package vn.edu.vtc.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Book;
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

  public Order viewOrderDetails(final int id) {
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
    } catch (final Exception e){
      return null;
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

  public List<Order> getAll(int id) {
    List<Order> listOrder = new ArrayList<>();
    try {
      final Connection con = DbUtil.getConnection();
      final String sql = "{call display_order(?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, id);
      callableStatement.executeUpdate();
      final ResultSet rs = callableStatement.executeQuery();
      while (rs.next()) {
        listOrder.add(getOrder(rs));
      }
      return listOrder;
    } catch (final SQLException e) {
      e.getSQLState();
      return null;
    } 
  }

  public List<Book> orderListBook(int id) {
    List<Book> listBook = new ArrayList<>();
    try {
     
      final Connection con = DbUtil.getConnection();
      final String sql = "{call orderListBook(?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, id);
      callableStatement.executeUpdate();
      final ResultSet rs = callableStatement.executeQuery();
      while (rs.next()) {
         Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setQuantity(rs.getInt("quantity"));
        book.setPrice(rs.getDouble("price"));
        listBook.add(book);
      }
    } catch (final SQLException e) {
      e.getSQLState();
      return null;
    }
    return listBook;  
  }

  public String displayPaymentMethods() {
    try {
      int i = 1;
      final Connection con = DbUtil.getConnection();
      final String sql = "{call display_payment_methods()}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      final ResultSet rs = callableStatement.executeQuery();
      String a = "\nPhuong thuc thanh toan";
      while (rs.next()) {
        a += "\n" + i + ". " + rs.getString(2);
        i++;
      }
      return a;
    } catch (final SQLException e) {
      e.getSQLState();
      return null;
    }
  }

  public String displayShippingUnit() {
    Presentation presentation = new Presentation();
    try {
      int i = 1;
      final Connection con = DbUtil.getConnection();
      final String sql = "{call display_shipping_unit()}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      final ResultSet rs = callableStatement.executeQuery();
      String a = "\nPhuong thuc van chuyen";
      while (rs.next()) {
        a += "\n" + i + ". " + rs.getString(2) + " - " + presentation.format(rs.getDouble(3));
        i++;
      }
      return a;
    } catch (final SQLException e) {
      e.getSQLState();
      return null;
    }
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

  public boolean insertOrder(String dateTime, int paymentMethod, int shippingUnit, int addressId, String orderStatus) {
    try {
      final Connection con = DbUtil.getConnection();
      final String sql = "{call insert_order(?,?,?,?,?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setString(1, dateTime);
      callableStatement.setInt(2, paymentMethod);
      callableStatement.setInt(3, shippingUnit);
      callableStatement.setInt(4, addressId);
      callableStatement.setString(5, orderStatus);
      callableStatement.executeUpdate();
      con.close();
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  public int orderId() {
    try {
      Connection con = DbUtil.getConnection();
      String se = "{call id_order}";
      CallableStatement d = con.prepareCall(se);
      ResultSet rs = d.executeQuery();
      while (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
    }
    return 0;
  }

  public int addressId(int id) {
    try {
      Connection con = DbUtil.getConnection();
      String sql = "{call id_address(?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, id);
      callableStatement.executeUpdate();
      final ResultSet rs = callableStatement.executeQuery();
      while (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
    }
    return 0;
  }
  
  public boolean insertBookOrder(int bookId, int orderId, int quantity, double price) {
    try {
      final Connection con = DbUtil.getConnection();
      final String sql = "{call order_detail(?,?,?,?)}";
      final CallableStatement callableStatement = con.prepareCall(sql);
      callableStatement.setInt(1, bookId);
      callableStatement.setInt(2, orderId);
      callableStatement.setInt(3, quantity);
      callableStatement.setDouble(4, price);
      callableStatement.executeUpdate();
      con.close();
      return true;
    } catch (final Exception e) {
      return false;
    }
  }
}