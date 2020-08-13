package vn.edu.vtc.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.persistance.Book;

public class BookDal {
    public static List<Book> getAll(){
        String sql = "select * from customers";
        List<Book> listBook =  new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                listBook.add(getBook(rs));
            }
        } catch (SQLException ex) {
        }
        return listBook;
    }

    private static Book getBook(final ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("customer_id"));
        book.setPublishingCompanyName(rs.getInt("publishingCompanyName"));
        book.setTitle(rs.getString("customer_address"));
        book.setAuthor(rs.getString("customer_address"));
        book.setPrice(rs.getDouble("customer_address"));
        book.setDetail(rs.getString("customer_address"));
        book.setQuantity(rs.getInt("customer_address"));
        book.setIsbn(rs.getString("customer_address"));
        return book;
    }
}