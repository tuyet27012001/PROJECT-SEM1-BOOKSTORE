package vn.edu.vtc.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.persistance.Book;

public class BookDal {
    public static List<Book> getAll() {
        List<Book> listBook = new ArrayList<>();
        try {
            Connection con = DbUtil.getConnection();
            String se = "{call display_book}";
            CallableStatement d = con.prepareCall(se);
            ResultSet rs = d.executeQuery();
            while (rs.next()) {
                listBook.add(getBook(rs));
            }
        } catch (SQLException ex) {
        }
        return listBook;
    }

    private static Book getBook(final ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setPublishingCompanyName(rs.getString("publishing_company_name"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPrice(rs.getDouble("price"));
        book.setDetail(rs.getString("detail"));
        book.setQuantity(rs.getInt("quantity"));
        book.setIsbn(rs.getString("isbn"));
        return book;
    }

    public static void displayCategory() {
        try {
            Connection con = DbUtil.getConnection();
            String se = "{call display_category}";
            CallableStatement d = con.prepareCall(se);
            ResultSet rs = d.executeQuery();
            while (rs.next()) {
                System.out.printf("|%-4d|%-35s |\n", rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.getSQLState();
        }
    }
}