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

    public static void viewCart() throws IOException {
        while (true) {
            app.clrscr();
            double sum = 0;
            orderBl.rFile();
            if (listBook.size() > 0) {
                System.out.println("Gio hang");
                System.out.println(
                        "===================================================================================================================");
                System.out.printf("|%-4s|%-50s|%-20s|%-15s|%-20s|\n", "Id", "Ten sach", "Don gia (vnd)", "So luong",
                        "So tien (vnd)");
                System.out.println(
                        "===================================================================================================================");
                for (final Book rs : listBook) {
                    System.out.printf("|%-4s|%-50s|%-20s|%-15d|%-20s|\n", rs.getBookId(), rs.getTitle(),
                            presentation.format(rs.getPrice()), rs.getQuantity(),
                            presentation.format(rs.getPrice() * rs.getQuantity()));
                    sum += rs.getPrice() * rs.getQuantity();
                }
                System.out.println(
                        "===================================================================================================================");
                System.out.printf("|%-20s %-55d %-15s %-14s %-5s|\n", "So luong mat hang : ", listBook.size(),
                        "Tong tien :", presentation.format(sum), " vnd");
                System.out.println(
                        "===================================================================================================================");
                System.out.println("1. Cap nhat gio hang");
                System.out.println("2. Dat hang");
                System.out.println("3. Thoat");
                while (true) {
                    System.out.printf("Chon : ");
                    int a = presentation.validateInteger();
                    if (a == 1) {
                        updateCart();
                        break;
                    } else if (a == 2) {
                        order(app.idCustomer);
                        return;
                    } else if (a == 3) {
                        return;
                    }
                }
            } else {
                System.out.println("Gio hang trong");
                sc.nextLine();
                return;

            }
        }
    }

    public static void updateCart() throws IOException {
        orderBl.rFile();
        System.out.printf("Nhap ma san pham de cap nhap hoac chon 0 de quay lai : ");
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
                System.out.println("Khong tim thay ma sach trong gio hang !");
                sc.nextLine();
            } else {
                int choose1;
                System.out.println("So luong sach trong kho : " + book.getQuantity());
                while (true) {
                    System.out.printf("Moi ban nhap so luong de cap nhat hoac nhap 0 de xoa san pham khoi gio hang : ");
                    choose1 = presentation.validateInteger();
                    if (choose1 == 0) {
                        System.out.printf("Ban co chac chan muon xoa (C/K) ? ");
                        String b = presentation.yesOrNo();
                        if (b.equalsIgnoreCase("c")) {
                            listBook.remove(index);
                            orderBl.wFile();
                            System.out.println("Xoa san pham thanh cong !");
                            sc.nextLine();
                            return;
                        } else {
                            return;
                        }
                    } else if (choose1 > 0) {
                        if (choose1 > book.getQuantity()) {
                            System.out.println("Ban da nhap qua so luong sach trong kho");
                            sc.nextLine();
                            return;
                        }
                        Book bo = listBook.get(index);
                        bo.setQuantity(choose1);
                        listBook.set(index, bo);
                        System.out.println("Cap nhat thanh cong");
                        orderBl.wFile();
                        sc.nextLine();
                        return;
                    } else {
                        System.out.println("Ban nhap sai ! Vui long nhap lai");
                    }
                }
            }
        }
    }

    public static void order(int idCustomer) throws IOException {
        orderBl.rFile();
        app.clrscr();
        Order order = new Order();
        int idAdd = 0;
        CustomerBl customerBl = new CustomerBl();
        customerBl.viewAddressList(idCustomer);
        if (customerBl.searchDefaultAddressId(idCustomer) == 0) {
            System.out.println("Ban chua co dia chi nao! Moi ban them dia chi .");
            customerPl.insertAddress(idCustomer);
            idAdd = orderBl.addressId(idCustomer);
        } else {
            System.out.println(customerBl.searchDefaultAddress(idCustomer));
            System.out.printf("Ban co muon su dung dia chi mac dinh (C/K)? ");
            String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("c")) {

                idAdd = customerBl.searchDefaultAddressId(idCustomer);
            } else {
                System.out.println(customerBl.viewAddressList(idCustomer));
                while (true) {
                    System.out.printf("Moi ban chon dia chi hoac nhap 0 de them dia chi moi : ");
                    int choose = presentation.validateInteger();
                    if (choose == 0) {
                        System.out.println("Moi ban them dia chi .");
                        customerPl.insertAddress(idCustomer);
                        idAdd = orderBl.addressId(idCustomer);
                        break;
                    } else if (customerBl.addressExists(idCustomer, choose) == true) {
                        idAdd = choose;
                        break;
                    } else {
                        System.out.println("Ban nhap sai xin vui long nhap lai !");
                    }
                }
            }
        }
        System.out.println(orderBl.displayShippingUnit());
        int choose;
        while (true) {
            System.out.printf("Chon : ");
            choose = presentation.validateInteger();
            if (choose < 4 && choose > 0) {
                break;
            } else
                System.out.println("Ban nhap sai moi ban nhap lai !");
        }
        int choose1;
        while (true) {
            System.out.println(orderBl.displayPaymentMethods());
            System.out.printf("Chon : ");
            choose1 = presentation.validateInteger();
            if (choose1 < 4 && choose1 > 0) {
                break;
            } else
                System.out.println("Ban nhap sai moi ban nhap lai !");
        }
        BookBl bookBl = new BookBl();
        System.out.printf("Ban co chac chan muon dat hang (C/K)? ");
        String yn = presentation.yesOrNo();
        if (yn.equalsIgnoreCase("c")) {
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

            System.out.println("Dat hang thanh cong");
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
        listBook.add(book);
        orderBl.wFile();
    }

    public void viewOrderDetails(int idOrder) {
        BookBl bookBl = new BookBl();
        double sum = 0;
        Order order = orderBl.viewOrderDetails(idOrder);
        if (order == null || orderBl.orderExists(app.idCustomer, idOrder) == false) {
            System.out.println("Ma don hang khong dung !\nNhan phim bat ky de quay lai .");
        } else {
            app.clrscr();
            List<Book> listB = new ArrayList<>();
            listB = orderBl.orderListBook(idOrder);
            int idAddress = order.getAddress();
            String address = orderBl.searchDefaultAddressId(idAddress);
            System.out.println("===========================================================");
            System.out.println("                      Bookstore");
            System.out.println("===========================================================");
            System.out.println("Ma hoa don : " + order.getOrderId());
            System.out.println("Trang thai don hang : " + order.getOrderStatus());
            System.out.println("Don vi van chuyen : " + order.getShippingUnit());
            System.out.println("Phuong thuc thanh toan : " + order.getPaymentMethod());
            System.out.println("Thoi gian dat hang : " +presentation.dateTime(order.getDateTime()));
            System.out.println("-----------------------------------------------------------");
            address = address.replace("Dia chi mac dinh\n", "");
            address = address.replace("\n=================================================", "");
            System.out.printf(address);
            System.out.println("\n-----------------------------------------------------------");
            System.out.println(
                    "===================================================================================================================");
            System.out.printf("|%-4s|%-50s|%-20s|%-15s|%-20s|\n", "Id", "Ten sach", "Don gia (vnd)", "So luong",
                    "So tien (vnd)");
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
            System.out.printf("|%-20s %-50d %-20s %-14s %-5s|\n", "So luong mat hang : ", listB.size(),
                    "Tong tien hang : ", presentation.format(sum), " vnd");
            System.out.printf("|%-71s %-21s %-13s %-5s|\n", "", "Phi van chuyen : ",
                    presentation.format(order.getShippingFee()), " vnd");
            System.out.printf("|%-71s %-20s %-14s %-5s|\n", "", "Tong tien : ",
                    presentation.format(sum + order.getShippingFee()), " vnd");
            System.out.println(
                    "===================================================================================================================");
            System.out.println("Cam on quy khach da mua sach tai Bookstore");
            if (order.getOrderStatus().equalsIgnoreCase("Da huy")) {
                sc.nextLine();
            } else{
                
                System.out.printf("Ban co muon huy don hang (C/K)? : ");
                String str = presentation.yesOrNo();
                if (str.equalsIgnoreCase("c")) {
                    for (int i = 0; i < listB.size(); i++) {
                        int id = listB.get(i).getBookId();
                        Book book = bookBl.viewBookDetail(id);
                        int quantityBook = book.getQuantity() + listB.get(i).getQuantity();
                        bookBl.updateQuantityBook(id, quantityBook);
                    }
                    orderBl.updateStatusAddress(idOrder, "Da huy");
                    System.out.println("Ban da huy don hang thanh cong.");
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
                System.out.println("Danh sach hoa don");
                System.out.println(
                        "==================================================================================================");
                System.out.printf("|%-4s|%-18s|%-20s|%-20s|%-30s| \n", "Id", "So luong mat hang", "Tong tien (vnd)",
                        "Thoi gian dat hang", "Trang thai don hang");
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
                    System.out.printf("Nhap ma don hang de xem chi tiet hoac nhap 0 de quay tro lai : ");
                    int choose = presentation.validateInteger();
                    if (orderBl.orderExists(app.idCustomer, choose) == true || choose == 0) {
                        idOrder = choose;
                        break;
                    } else {
                        System.out.println("Ban nhap sai !Moi ban nhap lai .");
                    }
                }
                if (idOrder == 0) {
                    return;
                } else {
                    viewOrderDetails(idOrder);
                }
            } else {
                System.out.println("Chua co don hang nao !");
                sc.nextLine();
                return;
            }
        }
    }
}
