package vn.edu.vtc.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.OrderBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Order;

public class OrderPl {
    private static App app = new App();
    public static List<Book> listBook = new ArrayList<Book>();
    private static OrderBl orderBl = new OrderBl();
    private static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerPl customerPl = new CustomerPl();
    private static BookBl bookBl = new BookBl();

    public static void viewCart() throws IOException {
        while (true) {
            app.clrscr();
            double sum = 0;
            orderBl.rFile();
            if (listBook.size() > 0) {
                int[] quantity = new int[100];
                int x = 0;
                System.out.println("Cart");
                System.out.println(
                        "===================================================================================================================");
                System.out.printf("|%-4s|%-50s|%-20s|%-15s|%-20s|\n", "Id", "Title", "Unit price (vnd)", "Quantity",
                        "Into money (vnd)");
                System.out.println(
                        "===================================================================================================================");
                for (final Book rs : listBook) {
                    System.out.printf("|%-4s|%-50s|%-20s|%-15d|%-20s|\n", rs.getBookId(), rs.getTitle(),
                            presentation.format(rs.getPrice()), rs.getQuantity(),
                            presentation.format(rs.getPrice() * rs.getQuantity()));
                    sum += rs.getPrice() * rs.getQuantity();
                    quantity[x] = rs.getQuantity();
                    x++;
                }
                System.out.println(
                        "===================================================================================================================");
                System.out.printf("|%-20s %-55d %-15s %-14s %-5s|\n", "Number of books : ", listBook.size(),
                        "Total money :", presentation.format(sum), " vnd");
                System.out.println(
                        "===================================================================================================================");
                System.out.println("1. Update cart");
                System.out.println("2. Order");
                System.out.println("3. Come back");

                while (true) {
                    System.out.printf("Choose : ");
                    int a = presentation.validateInteger();
                    orderBl.rFile();
                    int count = 0;
                    for (int i = 0; i < listBook.size(); i++) {
                        count++;
                        if (listBook.get(i).getQuantity() != quantity[i]) {
                            System.out.println("Cart has been changed press enter to update cart again !");
                            sc.nextLine();
                            count = 101;
                            break;
                        }
                    }
                    if (count == 101) {
                        break;
                    }
                    if (count == 0) {
                        System.out.println("Cart has been changed press enter to update cart again !");
                        sc.nextLine();
                        break;
                    }

                    if (a == 1) {
                        updateCart();
                        break;
                    } else if (a == 2) {
                        boolean bl = false;
                        for (int i = 0; i < listBook.size(); i++) {
                            int ch = listBook.get(i).getBookId();
                            Book book = bookBl.viewBookDetail(ch);
                            if (listBook.get(i).getQuantity() > book.getQuantity()) {
                                System.out.println("The number of books with id of " + ch
                                        + " in stock is not enough to invite you to update to order.");
                                bl = true;
                            }
                        }
                        if (bl == false) {
                            order(app.idCustomer);
                            return;
                        }
                        sc.nextLine();
                        break;
                    } else if (a == 3) {
                        return;
                    } else {
                        System.out.println("This function cannot be performed !");
                        sc.nextLine();
                        break;
                    }
                }
            } else {
                System.out.println("Your cart is empty");
                sc.nextLine();
                return;

            }
        }
    }

    public static void updateCart() throws IOException {
        orderBl.rFile();
        System.out.printf("Enter the book id to update or select 0 to go back: ");
        int choose = presentation.validateInteger();
        if (choose == 0) {
            return;
        } else {
            BookBl bookBl = new BookBl();
            Book book = bookBl.viewBookDetail(choose);
            int count = 0;
            int index = 0;
            boolean id = false;
            for (Book bookListBook : listBook) {
                if (bookListBook.getBookId() == choose) {
                    index = count;
                    id = true;
                }
                count++;
            }
            if (id == false) {
                System.out.println("Cannot find the book id you just entered in the shopping cart !");
                sc.nextLine();
            } else {
                int choose1;
                System.out.println("Number of books in stock : " + book.getQuantity());
                while (true) {
                    System.out.printf("Please enter the quantity to update or enter 0 to remove the product from the cart : ");
                    choose1 = presentation.validateInteger();
                    if (choose1 == 0) {
                        System.out.printf("Are you sure you want to delete (Y/N) ? ");
                        String b = presentation.yesOrNo();
                        if (b.equalsIgnoreCase("y")) {
                            listBook.remove(index);
                            orderBl.wFile();
                            System.out.println("Product deleted successfully !");
                            sc.nextLine();
                            return;
                        } else {
                            return;
                        }
                    } else if (choose1 > 0) {
                        if (choose1 > book.getQuantity()) {
                            System.out.println("You have imported more than the number of books in the stock.");
                            sc.nextLine();
                            return;
                        }
                        Book bo = listBook.get(index);
                        bo.setQuantity(choose1);
                        listBook.set(index, bo);
                        System.out.println("Update successful");
                        orderBl.wFile();
                        sc.nextLine();
                        return;
                    } else {
                        System.out.println("You entered wrong! Please re-enter.");
                    }
                }
            }
        }
    }

    public static void order(int idCustomer) throws IOException {
        List<Book> listBookOrder = new ArrayList<>();
        orderBl.rFile();
        app.clrscr();
        for (final Book rs : listBook) {
            listBookOrder.add(rs);
        }
        Order order = new Order();
        int idAdd = 0;
        CustomerBl customerBl = new CustomerBl();
        customerBl.viewAddressList(idCustomer);
        if (customerBl.searchDefaultAddressId(idCustomer) == 0) {
            System.out.println("You don't have an address yet! Please add your address .");
            customerPl.insertAddress(idCustomer);
            idAdd = orderBl.addressId(idCustomer);
        } else {
            System.out.println(customerBl.searchDefaultAddress(idCustomer));
            System.out.printf("Do you want to use the default address (Y/N)? ");
            String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("y")) {
                idAdd = customerBl.searchDefaultAddressId(idCustomer);
            } else {
                System.out.println(customerBl.viewAddressList(idCustomer));
                while (true) {
                    System.out.printf("Please select an address or enter 0 to add a new address: ");
                    int choose = presentation.validateInteger();
                    if (choose == 0) {
                        System.out.println("Please add your address.");
                        customerPl.insertAddress(idCustomer);
                        idAdd = orderBl.addressId(idCustomer);
                        break;
                    } else if (customerBl.addressExists(idCustomer, choose) == true) {
                        idAdd = choose;
                        break;
                    } else {
                        System.out.println("You entered incorrectly, please re-enter !");
                    }
                }
            }
        }
        System.out.println(orderBl.displayShippingUnit());
        int choose;
        while (true) {
            System.out.printf("Choose : ");
            choose = presentation.validateInteger();
            if (choose < 4 && choose > 0) {
                break;
            } else
                System.out.println("You entered incorrectly, please re-enter !");
        }
        int choose1;
        while (true) {
            System.out.println(orderBl.displayPaymentMethods());
            System.out.printf("Choose : ");
            choose1 = presentation.validateInteger();
            if (choose1 < 4 && choose1 > 0) {
                break;
            } else
                System.out.println("You entered incorrectly, please re-enter !");
        }
        app.clrscr();
        String a;
        double x;
        int sum = 0;
        System.out.println("Information order");
        if(choose == 1){a = "Giao hang tiet kiem"; x = 15000; }
        else if (choose == 2) { a = "Giao hang nhanh"; x = 20000;}
        else{a = "Viettel Post"; x = 18000;}   
        String b;
        if(choose1 == 1){b = "Thanh toan bang the quoc te Visa , Master, JCB"; }
        else if (choose1 == 2) { b = "Thanh toan khi nhan hang";}
        else{b = "The ATM noi dia / Internet Banking";} 
        String address = orderBl.searchDefaultAddressId(idAdd);
        System.out.println("Shipping unit : " + a);
        System.out.println("Payment methods : " + b);
        System.out.println("Delivery address : ");
        System.out.println("-----------------------------------------------------------");
        address = address.replace("Default address\n", "");
        address = address.replace("\n=================================================", "");
        System.out.printf(address);
        System.out.println("\n-----------------------------------------------------------");
        System.out.println(
                "===================================================================================================================");
        System.out.printf("|%-4s|%-50s|%-20s|%-15s|%-20s|\n", "Id", "Title", "Unit price (vnd)", "Quantity",
                "Into money (vnd)");
        System.out.println(
                "===================================================================================================================");
        for (Book rs : listBook) {
            Book book = bookBl.viewBookDetail(rs.getBookId());
            System.out.printf("|%-4s|%-50s|%-20s|%-15d|%-20s|\n", rs.getBookId(), book.getTitle(),
                    presentation.format(rs.getPrice()), rs.getQuantity(),
                    presentation.format(rs.getPrice() * rs.getQuantity()));
            sum += rs.getPrice() * rs.getQuantity();
        }
        System.out.println(
                "===================================================================================================================");
        System.out.printf("|%-20s %-50d %-20s %-14s %-5s|\n", "Number of books : ", listBook.size(),
                "Into money : ", presentation.format(sum), " vnd");
        System.out.printf("|%-71s %-21s %-13s %-5s|\n", "", "Transport fee : ",
                presentation.format(x), " vnd");
        System.out.printf("|%-71s %-20s %-14s %-5s|\n", "", "Total money : ",
                presentation.format(sum + order.getShippingFee()), " vnd");
        System.out.println(
                "===================================================================================================================");
        
        BookBl bookBl = new BookBl();
        System.out.printf("Are you sure you want to order (Y/N)? ");
        String yn = presentation.yesOrNo();
        if (yn.equalsIgnoreCase("y")) {
            boolean bl = false;
            int count = 0;
            orderBl.rFile();
            if (listBook.size() != listBookOrder.size()) {
                System.out.println("Your shopping cart has changed and cannot be ordered !");
                sc.nextLine();
                return;
            }
            for (int i = 0; i < listBook.size(); i++) {
                if (listBook.get(i).getQuantity() != listBookOrder.get(i).getQuantity()) {
                    System.out.println("Your shopping cart has changed and cannot be ordered !");
                    sc.nextLine();
                    return;
                }
            }
            for (int i = 0; i < listBook.size(); i++) {
                int ch = listBook.get(i).getBookId();
                Book book = bookBl.viewBookDetail(ch);
                if (listBook.get(i).getQuantity() > book.getQuantity()) {
                    System.out
                            .println("Number of books with id " + ch + " is invalid!\nPlease update cart again.");
                    bl = true;
                }
                count++;
            }
            if (bl == true) {
                sc.nextLine();
                return;
            }
            if (count == 0) {
                System.out.println("Cannot order.");
                sc.nextLine();
                return;
            }
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            String date = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            orderBl.insertOrder(date, choose, choose1, idAdd, "Cho xac nhan");
            int idOrder = orderBl.orderId();
            for (int i = listBook.size() - 1; i >= 0; i--) {
                orderBl.insertBookOrder(listBook.get(i).getBookId(), idOrder, listBook.get(i).getQuantity(),
                        listBook.get(i).getPrice());
                int id = listBook.get(i).getBookId();
                Book book = bookBl.viewBookDetail(id);
                int quantityBook = book.getQuantity() - listBook.get(i).getQuantity();
                bookBl.updateQuantityBook(id, quantityBook);
                listBook.remove(i);
            }
            orderBl.wFile();
            System.out.println("Order success.");
            sc.nextLine();
        }
    }

    public static void addCart(final Book book) throws IOException {
        orderBl.rFile();
        int count = 0;
        for (Book book1 : listBook) {
            if (book1.getBookId() == book.getBookId()) {
                book1.setQuantity(book1.getQuantity() + 1);
                listBook.set(count, book1);
                orderBl.wFile();
                return;
            }
            count++;
        }
        if (listBook.size() == 30) {
            System.out.println("Your shopping cart is full, please remove it or place an order so you can continue adding to the cart !");
            return;
        }
        System.out.println("Add to cart successfully.");
        sc.nextLine();
        listBook.add(book);
        orderBl.wFile();
    }

    public void viewOrderDetails(int idOrder) {
        BookBl bookBl = new BookBl();
        double sum = 0;
        Order order = orderBl.viewOrderDetails(idOrder);
        if (order == null || orderBl.orderExists(app.idCustomer, idOrder) == false) {
            System.out.println("Order id is incorrect! Press enter to go back .");
        } else {
            app.clrscr();
            List<Book> listB = new ArrayList<>();
            listB = orderBl.orderListBook(idOrder);
            int idAddress = order.getAddress();
            String address = orderBl.searchDefaultAddressId(idAddress);
            System.out.println("===========================================================");
            System.out.println("                      Bookstore");
            System.out.println("===========================================================");
            System.out.println("Order id : " + order.getOrderId());
            System.out.println("Order status : " + order.getOrderStatus());
            System.out.println("Shipping unit : " + order.getShippingUnit());
            System.out.println("Payment methods : " + order.getPaymentMethod());
            System.out.println("Time Order : " + presentation.dateTime(order.getDateTime()));
            System.out.println("-----------------------------------------------------------");
            address = address.replace("Default address\n", "");
            address = address.replace("\n=================================================", "");
            System.out.printf(address);
            System.out.println("\n-----------------------------------------------------------");
            System.out.println(
                    "===================================================================================================================");
            System.out.printf("|%-4s|%-50s|%-20s|%-15s|%-20s|\n", "Id", "Title", "Unit price (vnd)", "Quantity",
                    "Into money (vnd)");
            System.out.println(
                    "===================================================================================================================");
            for (Book rs : listB) {
                Book book = bookBl.viewBookDetail(rs.getBookId());
                System.out.printf("|%-4s|%-50s|%-20s|%-15d|%-20s|\n", rs.getBookId(), book.getTitle(),
                        presentation.format(rs.getPrice()), rs.getQuantity(),
                        presentation.format(rs.getPrice() * rs.getQuantity()));
                sum += rs.getPrice() * rs.getQuantity();
            }
            System.out.println(
                    "===================================================================================================================");
            System.out.printf("|%-20s %-50d %-20s %-14s %-5s|\n", "Number of books : ", listB.size(),
                    "Tong tien hang : ", presentation.format(sum), " vnd");
            System.out.printf("|%-71s %-21s %-13s %-5s|\n", "", "Transport fee : ",
                    presentation.format(order.getShippingFee()), " vnd");
            System.out.printf("|%-71s %-20s %-14s %-5s|\n", "", "Total money : ",
                    presentation.format(sum + order.getShippingFee()), " vnd");
            System.out.println(
                    "===================================================================================================================");
            System.out.println("Thank you for purchasing books at Bookstore.");
            if (order.getOrderStatus().equalsIgnoreCase("Da huy")) {
                sc.nextLine();
            } else {
                System.out.printf("Do you want to cancel your order (Y/N)? : ");
                String str = presentation.yesOrNo();
                if (str.equalsIgnoreCase("y")) {
                    for (int i = 0; i < listB.size(); i++) {
                        int id = listB.get(i).getBookId();
                        Book book = bookBl.viewBookDetail(id);
                        int quantityBook = book.getQuantity() + listB.get(i).getQuantity();
                        bookBl.updateQuantityBook(id, quantityBook);
                    }
                    orderBl.updateStatusAddress(idOrder, "Da huy");
                    System.out.println("You have successfully cancel orders.");
                    sc.nextLine();
                } else {
                    return;
                }
            }
        }
    }

    public void viewOrderList() {
        while (true) {
            app.clrscr();
            List<Order> listOrder = orderBl.getAll(app.idCustomer);

            if (listOrder.size() > 0) {
                System.out.println("List order");
                System.out.println(
                        "==================================================================================================");
                System.out.printf("|%-4s|%-18s|%-20s|%-20s|%-30s| \n", "Id", "Number of books", "Total money (vnd)",
                        "Time order", "Order status");
                System.out.println(
                        "==================================================================================================");
                for (Order rs : listOrder) {
                    List<Book> listB = new ArrayList<>();
                    listB = orderBl.orderListBook(rs.getOrderId());
                    double a = 0;
                    for (Book book : listB) {
                        a += book.getQuantity() * book.getPrice();
                    }
                    System.out.printf("|%-4d|%-18d|%-20s|%-20s|%-30s|\n", rs.getOrderId(), listB.size(),
                            presentation.format(a), rs.getDateTime(), rs.getOrderStatus());
                }
                System.out.println(
                        "==================================================================================================");
                int idOrder;
                while (true) {
                    System.out.printf("Enter order id to view details or enter 0 to go back : ");
                    int choose = presentation.validateInteger();
                    if (orderBl.orderExists(app.idCustomer, choose) == true || choose == 0) {
                        idOrder = choose;
                        break;
                    } else {
                        System.out.println("You entered incorrectly! Please re-enter .");
                    }
                }
                if (idOrder == 0) {
                    return;
                } else {
                    viewOrderDetails(idOrder);
                }
            } else {
                System.out.println("No orders yet !");
                sc.nextLine();
                return;
            }
        }
    }
}
