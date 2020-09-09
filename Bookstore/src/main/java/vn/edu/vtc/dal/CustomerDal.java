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

    public boolean login(final String ep, final String pass) {
        final App app = new App();
        try {
            final String sql = "{call search_phone_pass_customer(?, ?)}";
            final Connection con = DbUtil.getConnection();
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, ep);
            callableStatement.setString(2, pass);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            int count = 0;
            while (rs.next()) {
                final Customer cus = getCustomer(rs);
                app.idCustomer = cus.getIdCustomer();
                count++;
            }
            if (count > 0) {
                con.close();
                return true;
            }
        } catch (final Exception e) {
            return false;
        }

        try {
            final String sql1 = "{call search_email_pass_customer(?, ?)}";
            final Connection con1 = DbUtil.getConnection();
            final CallableStatement callableStatement1 = (CallableStatement) con1.prepareCall(sql1);
            callableStatement1.setString(1, ep);
            callableStatement1.setString(2, pass);
            callableStatement1.executeUpdate();
            final ResultSet rs = callableStatement1.executeQuery();
            int count1 = 0;
            while (rs.next()) {
                final Customer cus = getCustomer(rs);
                app.idCustomer = cus.getIdCustomer();
                count1++;
               
            }
            if (count1 > 0) {
               
                con1.close();
                return true;
            }
        } catch (final Exception e) {
            return false;
        }
        return false;
    }

    public boolean insertCustomer(final Customer cus) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call insert_customer(?,?,?,?,?,?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, cus.getName());
            callableStatement.setString(2, cus.getGender());
            callableStatement.setString(3, cus.getBirthDate());
            callableStatement.setString(4, cus.getPhone());
            callableStatement.setString(5, cus.getEmail());
            callableStatement.setString(6, cus.getPassword());
            callableStatement.executeUpdate();
            con.close();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    public boolean insertAddress(final String name, final String phone, final String city, final String district,
            final String address, final int id) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call insert_address(?,?,?,?,?,?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, name);
            callableStatement.setString(2, phone);
            callableStatement.setString(3, city);
            callableStatement.setString(4, district);
            callableStatement.setString(5, address);
            callableStatement.setInt(6, id);
            callableStatement.executeUpdate();
            con.close();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    public List<Customer> getAll() {
        final List<Customer> listCustomer = new ArrayList<>();
        try {
            final Connection con = DbUtil.getConnection();
            final String se = "{call display_customer}";
            final CallableStatement d = con.prepareCall(se);
            final ResultSet rs = d.executeQuery();
            while (rs.next()) {
                listCustomer.add(getCustomer(rs));
            }
        } catch (final SQLException ex) {
        }
        return listCustomer;
    }

    public Customer getCustomer(final ResultSet rs) {
        final Customer customer = new Customer();
        try {
            customer.setIdCustomer(rs.getInt("customer_id"));
            customer.setName(rs.getString("customer_name"));
            customer.setPhone(rs.getString("phonenumber"));
            customer.setGender(rs.getString("gender"));
            customer.setBirthDate(rs.getString("birth_date"));
            customer.setEmail(rs.getString("email"));
            customer.setPassword(rs.getString("password_customer"));

        } catch (final Exception e) {
            // TODO: handle exception
        }
        return customer;
    }

    public Customer detailCustomer(final int id) {
        Customer customer = new Customer();
        try {
            final String sql = "{call detail_customer(?)}";
            final Connection con = DbUtil.getConnection();
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
            con.close();
        } catch (final Exception e) {
        }
        return customer;
    }

    public boolean updateCustomerName(final int id, final String name) {
        return updateCustomer(id, name, "{call update_name_customer(?, ?)}");
    }

    public boolean updateCustomerPhone(final int id, final String phone) {
        return updateCustomer(id, phone, "{call update_phone_customer(?, ?)}");
    }

    public boolean updateCustomerEmail(final int id, final String email) {
        return updateCustomer(id, email, "{call update_email_customer(?, ?)}");
    }

    public boolean updateCustomerGender(final int id, final String gender) {
        return updateCustomer(id, gender, "{call update_gender_customer(?, ?)}");
    }

    public boolean updateCustomerBirth(final int id, final String birth) {
        return updateCustomer(id, birth, "{call update_birth_customer(?, ?)}");
    }

    public boolean updateCustomerPass(final int id, final String pass) {
        return updateCustomer(id, pass, "{call update_pass_customer(?, ?)}");
    }

    public boolean updateNameAddress(final int id, final String name) {
        return updateCustomer(id, name, "{call update_name_address(?, ?)}");
    }

    public boolean updatePhoneAddress(final int id, final String phone) {
        return updateCustomer(id, phone, "{call update_phone_address(?, ?)}");
    }

    public boolean updateDefaultAddress(final int id, final String str) {
        return updateCustomer(id, str, "{call update_default_address(?, ?)}");
    }

    public boolean updateStatusAddress(final int id) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call update_status_address(?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            con.close();
            return true;
        } catch (final SQLException e) {
            e.getSQLState();
            return false;
        }
    }

    public String searchDefaultAddress(final int id) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call search_default_address(?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String a = "Default address\n"+"Id address     : "+rs.getInt(1)+
                "\nName           : " + rs.getString(2)+
                "\nPhone number   : " + rs.getString(3)+
                "\nAddress        : " + rs.getString(6) + " , " + rs.getString(5) + " , " + rs.getString(4)+
                "\n=================================================";
                con.close();
                return a;
            }
        } catch (final SQLException e) {
            e.getSQLState();
            return null;
        }
        return null;
    }

    public int searchDefaultAddressId(final int id) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call search_default_address(?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (final SQLException e) {
            e.getSQLState();
            return 0;
        }
        return 0;
    }

    public boolean searchAddress(final int id) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = "{call search_address(?)}";
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                updateDefaultAddress(rs.getInt(1), null);
            }
            con.close();
            return true;
        } catch (final SQLException e) {
            e.getSQLState();
            return false;
        }
    }

    public boolean updateAddress(final int id, final String city, final String district, final String address) {
        updateCustomer(id, city, "{call update_city_address(?, ?)}");
        updateCustomer(id, district, "{call update_district_address(?, ?)}");
        return updateCustomer(id, address, "{call update_address(?, ?)}");
    }

    public boolean updateCustomer(final int id, final String str, final String procedure) {
        try {
            final Connection con = DbUtil.getConnection();
            final String sql = procedure;
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, str);
            callableStatement.executeUpdate();
            con.close();
            return true;
        } catch (final SQLException e) {
            e.getSQLState();
            return false;
        }
    }

    public String viewAddressList(final int id) {
        int count = 0;
        int idDefault = 0;
        String a;
        try {
            final String sql = "{call display_address(?)}";
            final Connection con = DbUtil.getConnection();
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            String str = searchDefaultAddress(id);
            if (str == null) {
                while (rs.next()) {
                    count++;
                    if (count == 1) {
                        idDefault = rs.getInt(1);
                    }
                }
                if (count == 0) {
                    return "No receiving address !";
                }
                if (idDefault != 0) {
                    updateDefaultAddress(idDefault, "Mac dinh");
                }
            }
            a = "Delivery address\n"+"=================================================\n"+searchDefaultAddress(id);
        } catch (final Exception e) {
            return null;
        }
        try {
            final String sql = "{call display_address(?)}";
            final Connection con = DbUtil.getConnection();
            final CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            final ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String b ="\nId address     : "+rs.getInt(1)+
                "\nName           : " + rs.getString(2)+
                "\nPhone number   : " + rs.getString(3)+
                "\nAddress        : " + rs.getString(6) + " , " + rs.getString(5) + " , " + rs.getString(4)+
                "\n-------------------------------------------------";
                a = a+b;
            }
            return a;
        } catch (final Exception e) {
            return null;
        }

    }

    public boolean addressExists(int idCustomer, int id) {
        int count = 0;
        try {
            final String sql = "{call address_exists(?,?)}";
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