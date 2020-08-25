package vn.edu.vtc.dal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import vn.edu.vtc.persistance.Book;

public class BookDalTest {
  BookDal bookDal = new BookDal();
  @Test
    public  void searchBookNameTest1(){
        try {
            final List<Book> listBook = bookDal.searchBookName("dong");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookNameTest2(){
        try {
            final List<Book> listBook = bookDal.searchBookName("tieng");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookNameTest3(){
        try {
            final List<Book> listBook = bookDal.searchBookName("nhanh");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void getAllBookTest1(){
        try {
            final List<Book> listBook = bookDal.getAll();
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void displayCategoryTest(){
        try {
            String result = bookDal.displayCategory();
            assertNotNull(result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryTest1(){
        try {
            final List<Book> listBook = bookDal.searchBookCategory(1);
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryTest2(){
        try {
            final List<Book> listBook = bookDal.searchBookCategory(2);
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryTest3(){
        try {
            final List<Book> listBook = bookDal.searchBookCategory(3);
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryTest4(){
        try {
            final List<Book> listBook = bookDal.searchBookCategory(9);
            assertNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryAndNameTest1(){
        try {
            final List<Book> listBook = bookDal.searchBookCategoryAndName(1,"999");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryAndNameTest2(){
        try {
            final List<Book> listBook = bookDal.searchBookCategoryAndName(9,"dong");
            assertNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryAndNameTest3(){
        try {
            final List<Book> listBook = bookDal.searchBookCategoryAndName(1,"khong");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }
    @Test
    public  void searchBookCategoryAndNameTest4(){
        try {
            final List<Book> listBook = bookDal.searchBookCategoryAndName(2,"ban");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public  void searchBookCategoryAndNameTest5(){
        try {
            final List<Book> listBook = bookDal.searchBookCategoryAndName(5,"ngu");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    

}