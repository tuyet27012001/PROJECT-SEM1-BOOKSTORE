package vn.edu.vtc.bl;

import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.dal.BookDal;
import vn.edu.vtc.persistance.Book;

public class BookBl {

    public static Scanner sc = new Scanner(System.in);
    private BookDal bookDal = new BookDal();

    public List<Book> viewBookList() {
        List<Book> listBook = bookDal.getAll();
        return listBook;
    }

    public String viewCategoryList() {
        return bookDal.viewCategoryList();
    }

    public List<Book> searchBookName(String name) {
        List<Book> listBook = bookDal.searchBookName(name);
        return listBook;
    }

    public List<Book> searchBookCategory(int id) {
        List<Book> listBook = bookDal.searchBookCategory(id);
        return listBook;
    }

    public List<Book> searchBookCategoryAndName(int id, String name) {
        List<Book> listBook = bookDal.searchBookCategoryAndName(id, name);
        return listBook;
    }

    public Book viewBookDetail(int id) {
        return bookDal.viewBookDetail(id);
    }

    public boolean updateQuantityBook(int id, int quantity) {
        return bookDal.updateQuantityBook(id, quantity);
    }
}