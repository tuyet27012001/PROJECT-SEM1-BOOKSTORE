package vn.edu.vtc.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtc.persistance.Book;

public class BookDal {
    public List<Book> getAll() {
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

    public boolean displayCategory() {
        try {
            Connection con = DbUtil.getConnection();
            String se = "{call display_category}";
            CallableStatement d = con.prepareCall(se);
            ResultSet rs = d.executeQuery();
            System.out.println("Danh muc");
            System.out.println("===========================================");
            System.out.printf("|%-4s|%-35s |\n", "Ma", "Ten danh muc");
            System.out.println("-------------------------------------------");
            while (rs.next()) {
                System.out.printf("|%-4d|%-35s |\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println("===========================================");
        } catch (SQLException e) {
            e.getSQLState();
        }
        return true;
    }

    public List<Book> searchBookName(String name) {
        List<Book> listBook = new ArrayList<>();
        try {
            Connection con = DbUtil.getConnection();
            String sql = "{call search_book_title(?)}";
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setString(1, name);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                listBook.add(getBook(rs));
            }
        } catch (SQLException ex) {
        }
        return listBook;
    }

    public List<Book> searchBookCategory(int id) {
        List<Book> listBook = new ArrayList<>();
        try {
            Connection con = DbUtil.getConnection();
            String sql = "{call search_book_category(?)}";
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                listBook.add(getBook(rs));
            }
        } catch (SQLException ex) {
        }
        return listBook;
    }

    public List<Book> searchBookCategoryAndName(int id, String name) {
        List<Book> listBook = new ArrayList<>();
        try {
            Connection con = DbUtil.getConnection();
            String sql = "{call search_book_category_name(?, ?)}";
            CallableStatement callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, name);
            callableStatement.executeUpdate();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                listBook.add(getBook(rs));
            }
        } catch (SQLException ex) {
        }
        return listBook;
    }

    private Book getBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setBookId(rs.getInt("book_id"));
            book.setPublishingCompanyName(rs.getString("publishing_company_name"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getDouble("price"));
            book.setDetail(rs.getString("detail"));
            book.setQuantity(rs.getInt("quantity"));
            book.setIsbn(rs.getString("isbn"));
        } catch (Exception e) {
        }
        return book;
    }

}