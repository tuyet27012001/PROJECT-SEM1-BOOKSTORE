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
    int page = listBook.size() / 10 + 1;
    while (true) {
      app.clrscr();
      int arr[] = new int[10];
      int n = 0;
      System.out.println("List book");
      System.out.println("Page : " + (k / 10 + 1) + "/" + page);
      System.out.println(
          "=============================================================================================================");
      System.out.printf("|%-4s|%-50s|%-30s|%20s| \n", "Id", "Title", "Author", "Price (VND)");
      System.out.println(
          "=============================================================================================================");

      if (listBook.size() - k < 10) {
        for (int i = k; i < listBook.size(); i++) {
          System.out.printf("|%-4d|%-50s|%-30s|%20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
              listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
          arr[n] = listBook.get(i).getBookId();
          n++;
        }
      } else {
        for (int i = k; i < k + 10; i++) {
          System.out.printf("|%-4d|%-50s|%-30s|%20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
              listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
              arr[n] = listBook.get(i).getBookId();
          n++;
        }
      }
      System.out.println(
          "=============================================================================================================");
      System.out.println("1. Previous page (Enter P) ");
      System.out.println("2. Next page (Enter N)");
      System.out.println("3. Selext page (Enter P + page number (eg : P1, P2..))");
      System.out.println("4. Select book (Enter id book)");
      System.out.println("5. Come back (Enter 0)");
      System.out.println("(The letters can be capital or lowercase)");
      String choose;
      while (true) {
        System.out.printf("#Choose : ");
        choose = sc.nextLine();
        choose = choose.trim();
        if(choose.length() == 0){
          System.out.println("Please do not leave it blank");
        }
        else if (choose.equalsIgnoreCase("0")) {
          return;
        }
        else if (choose.equalsIgnoreCase("p")) {
          if (k == 0) {
            System.out.println("No previous page invites you to choose again.");
          } else {
            k -= 10;
            break;
          }
        } else if (choose.equalsIgnoreCase("n")) {
          if (k == listBook.size() - listBook.size() % 10) {
            System.out.println("There is no following page inviting you to choose again.");
          } else {
            k += 10;
            break;
          }
        } else if (choose.indexOf("p") == 0 || choose.indexOf("P") == 0) {
          choose = choose.substring(1);
          if (presentation.validInteger(choose) == true) {
            int num = Integer.parseInt(choose);
            if (num > 0 && num <= page) {
              k = (num - 1) * 10;
              break;
            } else {
              System.out.println("Can not find page " + num + " , please enter again");
            }
          }
          else{
            System.out.println("You entered incorrectly, please re-enter .");
          }
        } else if (presentation.validInteger(choose) == true) {
          int num = Integer.parseInt(choose);
          boolean tf = false;
          for (int i = 0; i < 10; i++) {
            if (num == arr[i]) {
              viewBookDetail(num);
              tf = true;
              break;
            }
          }
          if (tf == false) {
            System.out.println("Cannot find books with id " + num + " on this page.");
          }
          else break;
        }
        else{
          System.out.println("You entered incorrectly, please re-enter .");
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
      System.out.printf("|%-4s|%-50s|%-30s|%20s| \n", "Id", "Title", "Author", "Price (VND)");
      System.out.println(
          "=============================================================================================================");
      for (int i = 0; i < listBook.size(); i++) {
        System.out.printf("|%-4d|%-50s|%-30s|%20s|\n", listBook.get(i).getBookId(), listBook.get(i).getTitle(),
            listBook.get(i).getAuthor(), presentation.format(listBook.get(i).getPrice()));
      }
      System.out.println(
          "=============================================================================================================");
      System.out.printf("Enter the book id to see details or enter 0 to go back : ");
      int idBook = presentation.validateInteger();
      boolean idBookS = false;
      if (idBook == 0) {
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