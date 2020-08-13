package vn.edu.vtc.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import vn.edu.vtc.persistance.Customer;

public class CustomerDal {
    public static boolean login(String ep, String pass){
        boolean log = false;
        try {
            String sql = "{call search_phone_customer(?)}";
            Connection con = DbUtil.getConnection();
            CallableStatement callableStatement = (CallableStatement) con.prepareCall(sql);
            callableStatement.setString(1, ep);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            String sql1 = "{call search_email_customer(?)}";
            CallableStatement callableStatement1 = (CallableStatement) con.prepareCall(sql1);
            callableStatement1.setString(1, ep);
            callableStatement1.executeUpdate();
            ResultSet rs1 = callableStatement1.executeQuery();
            int count1 = 0;
            while (rs1.next()) {
                count1++;
            }
            String sql2 = "{call search_password_customer(?)}";
            CallableStatement callableStatement2 = (CallableStatement) con.prepareCall(sql2);
            callableStatement2.setString(1, ep);
            callableStatement2.executeUpdate();
            ResultSet rs2 = callableStatement2.executeQuery();
            int count2 = 0;
            while (rs2.next()) {
                count2++;
            }
            
            if (count2 == 1 && (count == 1 || count1 == 1)) {
                log = true;
            } 
        } catch (SQLException e) {
            e.getSQLState();
        }
       return log;
    }

    public static List<Customer> getAll(){
        String sql = "select * from customers";
        List<Customer> listCustomer =  new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                listCustomer.add(getCustomer(rs));
            }
        } catch (SQLException ex) {
        }
        return listCustomer;
    }

    private static Customer getCustomer(final ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setIdCustomer(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setGender(rs.getString("customer_address"));
        customer.setBirthDate(rs.getDate("customer_address"));
        customer.setPhone(rs.getString("customer_address"));
        customer.setEmail(rs.getString("customer_address"));
        customer.setPassword(rs.getString("customer_address"));
        return customer;
    }

}