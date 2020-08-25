package vn.edu.vtc.pl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.App;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.OrderBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Order;

public class OrderPl {
    private static final String Order = null;
    private static App app = new App();
    private static List<Book> listBook = new ArrayList<Book>();
    private static OrderBl orderBl = new OrderBl();
    private static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerBl customerBl = new CustomerBl();

    public void cartManagement() throws IOException {
        viewCart();
    }

    public static void viewCart() throws IOException {
        app.clrscr();
        double sum = 0;
        rFile();
        if (listBook.size() > 0) {
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
        } else {
            System.out.println("Gio hang trong");
        }
        sc.nextLine();
    }

    public static void addCart(final Book book) throws IOException {
        rFile();
        listBook.add(book);
        wFile();
    }

    public static void wFile() throws IOException {
        final String nameFile = "cart" + app.idCustomer + ".dat";
        FileOutputStream out = null;
        ObjectOutputStream outputStream = null;
        try {
            out = new FileOutputStream(nameFile);
            System.out.println("ok11");
            outputStream = new ObjectOutputStream(out);
            System.out.println("ok12");
            outputStream.writeObject(listBook);
            System.out.println("ok13");
        } catch (final Exception e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void rFile() throws IOException {
        System.out.println(app.idCustomer);
        final String nameFile = "cart" + app.idCustomer + ".dat";
        FileInputStream inn = null;
        ObjectInputStream inputStream = null;
        try {
            inn = new FileInputStream(nameFile);
            System.out.println("ok15");
            inputStream = new ObjectInputStream(inn);
            System.out.println("ok16");
            listBook = (List<Book>) inputStream.readObject();
            System.out.println("ok38");
        } catch (final Exception e) {
            System.out.println(e);
        } finally {
            if (inn != null) {
                inn.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void detailOrder(int idOrder) {
        double sum = 0;
        Order order = orderBl.detailOrder(idOrder);
        if (order == null) {
            System.out.println("Ma don hang khong dung !\nNhan phim bat ky de quay lai .");
        } else {
            int idAddress = order.getAddress();
            String address = orderBl.searchDefaultAddressId(idAddress);
            System.out.println("===========================================================");
            System.out.println("                      Bookstore");
            System.out.println("===========================================================");
            System.out.printf("Ma hoa don : \n", order.getOrderId());
            System.out.printf("Trang thai don hang : \n", order.getOrderStatus());
            System.out.printf("Don vi van chuyen : \n", order.getShippingUnit());
            System.out.printf("Phuong thuc thanh toan : \n", order.getPaymentMethod());
            System.out.printf("Thoi gian dat hang : \n", order.getDateTime());
            System.out.println("-----------------------------------------------------------");
            System.out.printf(address);
            System.out.println("\n-----------------------------------------------------------");
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
            System.out.printf("|%-20s %-50d %-20s %-14s %-5s|\n", "So luong mat hang : ", listBook.size(),
                    "Tong tien hang : ", presentation.format(sum), " vnd");
            System.out.printf("|%-50s|%-20s|%-10s|\n", "", "Phi van chuyen : ",
                    presentation.format(order.getShippingFee()));
            System.out.printf("|%-50s|%-20s|%-10s|\n", "", "Tong tien : ",
                    presentation.format(sum + order.getShippingFee()));
            System.out.println(
                    "===================================================================================================================");
            System.out.println("Cam on quy khach da mua sach tai Bookstore");
        }
    }

    public void displayOrder() {
        List<Order> listOrder = orderBl.getAll();
        // if (listOrder.size() > 0) {
            while (true) {
                app.clrscr();
                System.out.println("Danh sach hoa don");
                System.out.println(
                        "=============================================================================================================");
                System.out.printf("|%-4s|%-15s|%-20s|%-20s|%-30s| \n", "Id", "So luong mat hang", "Tong tien (vnd)",
                        "Thoi gian dat hang", "Trang thai don hang");
                System.out.println(
                        "=============================================================================================================");
                for (Order rs : listOrder) {
                    System.out.printf("|%-4d|%-15s|%-20s|%-20s|%-30s|\n", rs.getOrderId());
                }
                System.out.println(
                        "=============================================================================================================");
                int idOrder = checkIdOrder(app.idCustomer, "Nhap ma don hang de xem chi tiet hoac nhap 0 de quay tro lai : ");
                if (idOrder == 0) {
                    return;
                } else {
                    detailOrder(idOrder);
                }
            // }
        }
    }

    public int checkIdOrder(int id, String str) {
        while (true) {
          System.out.printf(str);
          int choose = presentation.validateInteger();
          if (orderBl.orderExists(id, choose) == true || choose == 0) {
            return choose;
          } else {
            System.out.println("Ban nhap sai !Moi ban nhap lai .");
          }
        }
    }

}
