package vn.edu.vtc.bl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import vn.edu.vtc.persistance.Book;


public class BookBlTest {
  BookBl bookBl = new BookBl();
  @Test
    public  void searchBookNameTest1(){
        try {
            List<Book> listBook = bookBl.searchBookName("dong");
            assertNotNull(listBook);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

}