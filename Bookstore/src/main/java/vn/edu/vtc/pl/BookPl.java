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
      String[] arr = { "View list book", "View category", "Search", "Come back" };
      int choose = app.menu(arr, "Welcome to Bookstore");
      switch (choose) {
        case 1:
          List<Book> listBook = bookBl.viewBookList();
          viewBookList(listBook);
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

  public void viewBookList(List<Book> listBook) {
    int k = 0;
    while (true) {
      app.clrscr();
      System.out.println("List book");
      System.out.println("Page : "+(k/10+1));
      System.out.println(
          "=============================================================================================================");
      System.out.printf("|%-4s|%-50s|%-30s|%-20s| \n", "Id", "Title", "Author", "Price (vnd)");
      System.out.println(
          "=============================================================================================================");
      
      if(listBook.size() - k < 10 ){
        for (int i = k; i < listBook.size(); i++) {
          System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
              listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
        }
      }
      else{
        for (int i = k; i < k + 10; i++) {
          System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
              listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
        }
      }
      System.out.println(
          "=============================================================================================================");
      System.out.println("1. Previous page");
      System.out.println("2. Next page");
      System.out.println("3. Select book");
      System.out.println("4. Come back");
      int choose;
      while (true) {
        System.out.printf("#Choose : ");
        choose = presentation.validateInteger();
        if (choose == 1) {
          if (k == 0) {
            System.out.println("No previous page invites you to choose again.");
          } else {
            k -= 10;
            break;
          }
        } else if (choose == 2) {
          if (k == listBook.size() - listBook.size()%10) {
            System.out.println("There is no following page inviting you to choose again.");
          } else {
            k += 10;
            break;
          }
        } else if (choose == 3) {
          System.out.printf("Enter the book id to see details : ");
          int idBook = presentation.validateInteger();
          if (idBook > k && idBook < k + 11) {
            viewBookDetail(idBook);
          } else {
            System.out.println("Cannot find books with id " + idBook + " on this page.");
            sc.nextLine();
          }
          break;
        } else if (choose == 4) {
          return;
        } else {
          System.out.println("You entered incorrectly, please re-enter.");
        }
      }
    }
  }

  public void viewBookListSearch(List<Book> listBook) {
    while (true) {
      app.clrscr();
      System.out.println("List book");
      System.out.println(
          "=============================================================================================================");
      System.out.printf("|%-4s|%-50s|%-30s|%-20s| \n", "Id", "Title", "Author", "Price (vnd)");
      System.out.println(
          "=============================================================================================================");
      for (int i = 0; i < listBook.size(); i++) {
        System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
            listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
      }
      System.out.println(
          "=============================================================================================================");
      System.out.printf("Enter the book id to see details or enter 0 to go back : ");
      int idBook = presentation.validateInteger();
      boolean idBookS = false;
      if(idBook == 0){
        return;
      }
      for (int i = 0; i < listBook.size(); i++) {
        if (idBook == listBook.get(i).getBookId()) {
          viewBookDetail(idBook);
          idBookS = true;
          break;
        }
      }
      if (idBookS == false) {
        System.out.println("Cannot find books with id " + idBook + " on this page.");
        sc.nextLine();
      }

    }
  }

  public void menuSearchBook() {
    while (true) {
      app.clrscr();
      String[] menuSearch = { "Search by name", "Search by category", "Search by category and name", "Come back" };
      int choose = app.menu(menuSearch, "Welcome to Bookstore");
      switch (choose) {
        case 1:
          app.clrscr();
          searchBookName();
          break;
        case 2:
          app.clrscr();
          System.out.println(bookBl.viewCategoryList());
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
      System.out.printf("Enter a book title/author name : ");
      name = sc.nextLine();
      name = name.trim();
      if (name.isEmpty() == false) {
        break;
      } else {
        System.out.println("Please do not to blank . Please re-enter !");

      }
    }
    List<Book> listBook = bookBl.searchBookName(name);
    if (listBook == null) {
      System.out.println("Can't find the book .");
      sc.nextLine();
    } else {
      viewBookListSearch(listBook);
    }
  }

  public void searchBookCategory() {
    int a;
    while (true) {
      app.clrscr();
      System.out.println(bookBl.viewCategoryList());
      while (true) {
        System.out.printf("Enter the category code to view the list in the category or enter 0 to go back : ");
        a = presentation.validateInteger();
        if (a >= 0 && a < 7)
          break;
        else
          System.out.println("You entered incorrectly, please re-enter .");
      }
      if (a == 0) {
        break;
      } else {
        List<Book> listBook = bookBl.searchBookCategory(a);
        if (listBook.isEmpty() == true) {
          System.out.println("Can't find the book.");
          sc.nextLine();
        } else {
          viewBookList(listBook);
        }
      }

    }
  }

  public void searchBookCategoryAndName() {
    System.out.println(bookBl.viewCategoryList());
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
      viewBookListSearch(listBook);
    }
  }

  public void viewBookDetail(int id) {
    while (true) {
      app.clrscr();
      System.out.println("Book information with id: : " + id);
      Book book = bookBl.viewBookDetail(id);
      System.out.println("------------------------------------------------------");
      if (book == null) {
        System.out.println("The book id is not correct! Press the enter key to go back.");
      } else {
        System.out.println("Title : " + book.getTitle());
        System.out.println("Author : " + book.getAuthor());
        System.out.println("Publishing company : " + book.getPublishingCompanyName());
        System.out.println("Price : " + presentation.format(book.getPrice()));
        System.out.println("Quantity : " + book.getQuantity());
        System.out.println("Detail : " + book.getDetail());
        System.out.println("=======================================================");
        if (book.getQuantity() == 0) {
          System.out.println("Sold out");
          sc.nextLine();
          return;
        }
        System.out.printf("Do you want to add to cart (Y/N)? ");
        final String ck = presentation.yesOrNo();
        if (ck.equalsIgnoreCase("y")) {
          if (app.idCustomer != 0) {
            book.setQuantity(1);
            try {
              orderPl.addCart(book);
              return;
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            System.out.printf("You need to log in to add it to the cart.\nDo you want to login. (Y/N)?");
            String ck1 = presentation.yesOrNo();
            if (ck1.equalsIgnoreCase("y")) {
              customerPl.login();
            }
          }
        } else {
          return;
        }
      }
    }
  }
}