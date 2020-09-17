package vn.edu.vtc.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {
   private static Connection connection;
   private static String url = "jdbc:mysql://localhost:3306/Bookstore?useUnicode=true&characterEncoding=UTF-8";
   private static String user = "root";
   private static String password = "123456789";
   
   public static Connection getConnection() throws SQLException {
      connection = DriverManager.getConnection(url, user, password);
      return connection;
  }
}