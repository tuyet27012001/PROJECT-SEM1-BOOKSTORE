package vn.edu.vtc.dal;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.App;
import vn.edu.vtc.persistance.Customer;

public class CustomerDal {

    public boolean login(String ep, String pass) {
        App app = new App();
        boolean log = false;
        try {
            String sql = "{call search_phone_pass_customer(?, ?)}";
            Connection con = DbUtil.getConnection();
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, ep);
            callableStatement.setString(2, pass);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            int count = 0;
            while (rs.next()) {
                Customer cus = getCustomer(rs);
                app.idCustomer = cus.getIdCustomer();
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
                Customer cus = getCustomer(rs);
                app.idCustomer = cus.getIdCustomer();
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
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, cus.getName());
            callableStatement.setString(2, cus.getGender());
            callableStatement.setString(3, cus.getBirthDate());
            callableStatement.setString(4, cus.getPhone());
            callableStatement.setString(5, cus.getEmail());
            callableStatement.setString(6, cus.getPassword());
            callableStatement.executeUpdate();
            reg = true;
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return reg;
    }


    public List<Customer> getAll() {
        List<Customer> listCustomer = new ArrayList<>();
        try {
            Connection con = DbUtil.getConnection();
            String se = "{call display_customer}";
            CallableStatement d = con.prepareCall(se);
            ResultSet rs = d.executeQuery();
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
            customer.setPhone(rs.getString("phonenumber"));
            customer.setGender(rs.getString("gender"));
            customer.setBirthDate(rs.getString("birth_date"));
            customer.setEmail(rs.getString("email"));
            customer.setPassword(rs.getString("password_customer"));
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return customer;
    }

    public Customer detailCustomer(int id){
        Customer customer = new Customer();
        try {
            String sql = "{call detail_customer(?)}";
            Connection con = DbUtil.getConnection();
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, 1);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
            con.close();
        } catch (Exception e) {
        }
        return customer;
    }

    public boolean updateCustomerName(int id, String name){
        return  updateCustomer(id, name, "{call update_name_customer(?, ?)}");
    }

    public boolean updateCustomerPhone(int id, String phone){
        return  updateCustomer(id, phone, "{call update_phone_customer(?, ?)}");
    }

    public boolean updateCustomerEmail(int id, String email){
        return  updateCustomer(id, email, "{call update_email_customer(?, ?)}");
    }

    public boolean updateCustomerGender(int id, String gender){
        return  updateCustomer(id, gender, "{call update_gender_customer(?, ?)}");
    }


    public boolean updateCustomerBirth(int id, String birth){
        return  updateCustomer(id, birth, "{call update_birth_customer(?, ?)}");
    }

    public boolean updateCustomerPass(int id, String pass){
       return  updateCustomer(id, pass, "{call update_pass_customer(?, ?)}");
    }

    public boolean updateCustomer(int id, String str, String procedure){
        try {
            Connection con = DbUtil.getConnection();
            String sql = procedure;
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, str);
            callableStatement.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.getSQLState();
            return false;
        }
    }

}