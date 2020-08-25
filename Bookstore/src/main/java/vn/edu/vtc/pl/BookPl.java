package vn.edu.vtc.pl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Book;

public class BookPl {
  App app = new App();
  BookBl bookBl = new BookBl();
  Presentation presentation = new Presentation();
  OrderPl orderPl = new OrderPl();
  CustomerPl customerPl = new CustomerPl();
  Scanner sc = new Scanner(System.in);

  public void menuBook() {
    while (true) {
      app.clrscr();
      String[] arr = { "Danh sach Sach", "Danh muc", "Tim kiem", "Thoat" };
      int choose = app.menu(arr, "Chao mung ban den voi Bookstore");
      switch (choose) {
        case 1:
          List<Book> listBook = bookBl.displayBook();
          displayBook(listBook);
          break;
        case 2:
          searchBookCategory();
          break;
        case 3:
          menuSearchBook();
          break;
        case 4:
          return;
        default:
          break;
      }
    }
  }

  public void displayBook(List<Book> listBook) {
    while (true) {
      app.clrscr();
      System.out.println(
          "=============================================================================================================");
      System.out.printf("|%-4s|%-50s|%-30s|%-20s| \n", "Id", "Ten sach", "Tac gia", "Gia (vnd)");
      System.out.println(
          "=============================================================================================================");
      for (Book rs : listBook) {
        System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", rs.getBookId(), rs.getTitle(), rs.getAuthor(),presentation.format(rs.getPrice()));
      }
      System.out.println(
          "=============================================================================================================");
      System.out.printf("Nhap ma sach de xem chi tiet hoac nhap 0 de quay tro lai : ");
      int idBook = presentation.validateInteger();
      if (idBook == 0) {
        return;
      } else {
        detailBook(idBook);
      }
    }
  }

  public void menuSearchBook() {
    while (true) {
      app.clrscr();
      String[] menuSearch = { "Tim kiem theo ten", "Tim kiem theo danh muc", "Tim kiem theo ten va danh muc", "Thoat" };
      int choose = app.menu(menuSearch, "Chao mung ban den voi Bookstore");
      switch (choose) {
        case 1:
          app.clrscr();
          searchBookName();
          break;
        case 2:
          app.clrscr();
          System.out.println(bookBl.displayCategory());
          searchBookCategory();
          break;
        case 3:
          app.clrscr();
          searchBookCategoryAndName();
          break;
        case 4:
          return;
        default:
          break;
      }
    }
  }

  public void searchBookName() {
    String name;
    while (true) {
      System.out.printf("Nhap ten sach/tac gia : ");
      name = sc.nextLine();
      name = name.trim();
      if (name.isEmpty() == false) {
        break;
      } else {
        System.out.println("Vui long khong de trong . Moi nhap lai !");

      }
    }
    List<Book> listBook = bookBl.searchBookName(name);
    if (listBook == null) {
      System.out.println("Khong tim thay sach");
      sc.nextLine();
    } else {
      displayBook(listBook);
    }
  }

  public void searchBookCategory() {
    int a;
    while (true) {
      app.clrscr();
      System.out.println(bookBl.displayCategory());
      while (true) {
        System.out.printf("Nhap ma danh muc de xem danh sach sach trong danh muc hoac nhan 0 de quay lai : ");
        a = presentation.validateInteger();
        if (a >= 0 && a < 7)
          break;
        else
          System.out.println("Ban nhap sai moi ban nhap lai .");
      }
      if (a == 0) {
        break;
      } else {
        List<Book> listBook = bookBl.searchBookCategory(a);
        if (listBook.isEmpty() == true) {
          System.out.println("Khong tim thay sach");
          sc.nextLine();
        } else {
          displayBook(listBook);
        }
      }

    }
  }

  public void searchBookCategoryAndName() {
    System.out.println(bookBl.displayCategory());
    int id;
    while (true) {
      System.out.printf("Nhap ma danh muc : ");
      id = sc.nextInt();
      if (id > 0 && id < 7)
        break;
    }
    sc.nextLine();
    String name;
    while (true) {
      System.out.printf("Nhap ten sach/tac gia : ");
      name = sc.nextLine();
      name = name.trim();
      if (name.isEmpty() == false) {
        break;
      } else {
        System.out.println("Vui long khong de trong . Moi nhap lai !");

      }
    }
    List<Book> listBook = bookBl.searchBookCategoryAndName(id, name);
    if (listBook == null) {
      System.out.println("Khong tim thay sach");
      sc.nextLine();
    } else {
      displayBook(listBook);
    }
  }

  public void detailBook(int id) {
    while (true) {
      app.clrscr();
      System.out.println("Thong tin sach ma : " + id);
      Book book = bookBl.detailBook(id);
      System.out.println("------------------------------------------------------");
      if (book == null) {
        System.out.println("Ma sach khong dung !\nNhan phim bat ky de quay lai .");
      } else {
        System.out.println("Ten sach : " + book.getTitle());
        System.out.println("Tac gia : " + book.getAuthor());
        System.out.println("Nha xuat ban : " + book.getPublishingCompanyName());
        System.out.println("Gia : " + presentation.format(book.getPrice()) );
        System.out.println("So luong : " + book.getQuantity());
        System.out.println("Mo ta : " + book.getDetail());
        System.out.println("=======================================================");
        System.out.printf("Ban co muon them vao gio hang (C/K)? ");
        final String ck = presentation.yesOrNo();
        if (app.idCustomer != 0) {
          if (ck.equalsIgnoreCase("c")) {
            book.setQuantity(1);
            try {
              orderPl.addCart(book);
              System.out.println("Them vao gio hang thanh cong");
              sc.nextLine();
              return;
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            return;
          }
        } else {
          System.out.printf("Ban can dang nhap de them vao gio hang .\nBan co muon dang nhap (C/K)?");
          String ck1 = presentation.yesOrNo();
          if (ck1.equalsIgnoreCase("c")) {
            customerPl.login();
          }
        }
      }
    }
  }
}