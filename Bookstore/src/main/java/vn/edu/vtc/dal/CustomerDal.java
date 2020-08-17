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
    // public boolean login(String ep, String pass) {
    // boolean log = false;
    // try {
    // String sql = "{call search_phone_customer(?)}";
    // Connection con = DbUtil.getConnection();
    // CallableStatement callableStatement = (CallableStatement)
    // con.prepareCall(sql);
    // callableStatement.setString(1, ep);
    // callableStatement.executeUpdate();
    // ResultSet rs = callableStatement.executeQuery();
    // int count = 0;
    // while (rs.next()) {
    // count++;
    // }
    // String sql1 = "{call search_email_customer(?)}";
    // CallableStatement callableStatement1 = (CallableStatement)
    // con.prepareCall(sql1);
    // callableStatement1.setString(1, ep);
    // callableStatement1.executeUpdate();
    // ResultSet rs1 = callableStatement1.executeQuery();
    // int count1 = 0;
    // while (rs1.next()) {
    // count1++;
    // }
    // String sql2 = "{call search_password_customer(?)}";
    // CallableStatement callableStatement2 = (CallableStatement)
    // con.prepareCall(sql2);
    // callableStatement2.setString(1, pass);
    // callableStatement2.executeUpdate();
    // ResultSet rs2 = callableStatement2.executeQuery();
    // int count2 = 0;
    // while (rs2.next()) {
    // count2++;
    // }

    // if (count2 == 1 && (count == 1 || count1 == 1)) {
    // log = true;
    // }
    // } catch (SQLException e) {
    // e.getSQLState();
    // }
    // return log;
    // }

    public boolean login(String ep, String pass) {
        boolean log = false;
        try {
            String sql = "{call search_phone_pass_customer(?, ?)}";
            Connection con = DbUtil.getConnection();
            CallableStatement callableStatement = (CallableStatement) con.prepareCall(sql);
            callableStatement.setString(1, ep);
            callableStatement.setString(2, pass);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                log = true;
                con.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            String sql1 = "{call search_email_pass_customer(?, ?)}";
            Connection con1 = DbUtil.getConnection();
            CallableStatement callableStatement1 = (CallableStatement) con1.prepareCall(sql1);
            callableStatement1.setString(1, ep);
            callableStatement1.setString(2, pass);
            callableStatement1.executeUpdate();
            ResultSet rs = callableStatement1.executeQuery();
            int count1 = 0;
            while (rs.next()) {
                count1++;
            }
            if (count1 > 0) {
                log = true;
                con1.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return log;
    }

    public boolean insertCustomer(Customer cus) {
        boolean reg = false;
        try {
            Connection con = DbUtil.getConnection();
            String sql = "{call insert_customer(?,?,?,?,?,?)}";
            CallableStatement callableStatement = (CallableStatement) con.prepareCall(sql);
            callableStatement.setString(1, cus.getName());
            callableStatement.setString(2, cus.getGender());
            callableStatement.setString(3, cus.getBirthDate());
            callableStatement.setString(4, cus.getPhone());
            callableStatement.setString(5, cus.getEmail());
            callableStatement.setString(6, cus.getPassword());
            callableStatement.executeUpdate();
            reg = true;
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return reg;
    }

    public List<Customer> getAll() {
        String sql = "select * from customers";
        List<Customer> listCustomer = new ArrayList<>();
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

    public Customer getCustomer(final ResultSet rs) {

        Customer customer = new Customer();
        try {
            customer.setIdCustomer(rs.getInt("customer_id"));
            customer.setName(rs.getString("customer_name"));
            customer.setGender(rs.getString("gender"));
            customer.setBirthDate(rs.getString("birth_date"));
            customer.setPhone(rs.getString("phonenumber"));
            customer.setEmail(rs.getString("email"));
            customer.setPassword(rs.getString("password_customer"));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return customer;
    }

}