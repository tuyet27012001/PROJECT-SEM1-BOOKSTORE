package vn.edu.vtc.dal;

import static org.junit.Assert.assertNotNull;

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

}