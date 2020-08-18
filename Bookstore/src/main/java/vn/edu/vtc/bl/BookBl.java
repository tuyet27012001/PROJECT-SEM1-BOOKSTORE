package vn.edu.vtc.bl;

import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.dal.BookDal;
import vn.edu.vtc.persistance.Book;

public class BookBl {
  public static Scanner sc = new Scanner(System.in);
  
  public List<Book> displayBook(){
    List<Book> listBook = BookDal.getAll();
    return listBook;
  }



  
}