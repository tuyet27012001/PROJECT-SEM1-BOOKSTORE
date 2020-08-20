package vn.edu.vtc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Book;
import vn.edu.vtc.persistance.Customer;

public class App {
    static Scanner sc = new Scanner(System.in);
    private static Presentation presentation = new Presentation();
    private static CustomerBl customerBl = new CustomerBl();
    private static BookBl bookBl = new BookBl();
    public static int idCustomer = 0;

    public static void main(final String[] args) throws SQLException {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            String[] menuMain = { "Sản phẩm", "Đăng nhập", "Đăng ký", "Thoát" };
            int choose = menu(menuMain, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    menuBook();
                    break;
                case 2:
                    clrscr();
                    login();
                    break;
                case 3:
                    clrscr();
                    register();
                    break;
                case 4:
                    String name = "fruuew vTGS";
                    String regex = "([A-Za-z ]{0,})";
                    System.out.println(name.matches(regex));
                    sc.nextLine();
                    // System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

    public static int menu(final String arr[], final String title) {
        int num;
        clrscr();
        System.out.println("=======================================================");
        System.out.printf("||         %-41s ||\n", title);
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("|| %-1d, %-46s ||\n", i + 1, arr[i]);
        }
        System.out.println("=======================================================");
        System.out.printf("#Chon : ");
        while (true) {
            num = presentation.validateInteger();
            if (num >= 0 && num <= arr.length) {
                break;
            } else {
                System.out.printf("Ban nhap sai !\nXin vui long nhap lại : ");
            }
        }
        return num;
    }

    public static void login() {
        System.out.println("                Dang nhap");
        System.out.println("-------------------------------------------");
        String ep;
        while (true) {
            System.out.printf("Email/So dien thoai : ");
            ep = sc.nextLine();
            if (presentation.validEmail(ep) == true || presentation.validPhone(ep) == true) {
                break;
            } else {
                System.out.printf("Ban nhap sai vui long nhap lai !\n");
            }
        }
        System.out.printf("Mat khau : ");
        String pass = customerBl.password();
        pass = customerBl.md5(pass);
        if (customerBl.login(ep, pass) == true) {
            menuCustomer();
        }
    }

    public static void clrscr() {
        // Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static void register() {
        System.out.println("                    Dang ky");
        System.out.println("--------------------------------------------------");
        List<Customer> listCustomer = customerBl.listCustomer();
        Customer cus = new Customer();
        while (true) {
            System.out.printf("Ten khach hang : ");
            String n = sc.nextLine();
            n = n.trim();
            if (presentation.validName(n) == true && n.isEmpty() == false) {
                cus.setName(n);
                break;
            } else {
                System.out.println("Ban nhap sai !\nXin vui long nhap lai.");
            }
        }
        while (true) {
            System.out.printf("So dien thoai : ");
            String p = sc.nextLine();
            p = p.trim();
            for (int i = 0; i < 0; i++) {
                if (p.equals(listCustomer.get(i).getPhone())) {
                    System.out.printf("So dien thoai da ton tai b co muon dang nhap ?(C/K) : ");
                    String ck = presentation.yesOrNo();
                    if (ck.equalsIgnoreCase("c")) {
                        login();
                    } else
                        break;
                }
            }
            if (presentation.validPhone(p) == true) {
                cus.setPhone(p);
                break;
            } else {
                System.out.println("Ban nhap sai !Xin vui long nhap lai.");
            }
        }
        while (true) {
            System.out.printf("Email : ");
            String e = sc.nextLine();
            e = e.trim();
            for (int i = 0; i < 0; i++) {
                if (e.equals(listCustomer.get(i).getEmail())) {
                    System.out.printf("Email da ton tai b co muon dang nhap ?(C/K) : ");
                    final String ck = presentation.yesOrNo();
                    if (ck.equalsIgnoreCase("c")) {
                        login();
                    } else
                        break;
                }
            }
            if (presentation.validEmail(e) == true || e == null) {
                cus.setEmail(e);
                break;
            } else {
                System.out.println("Ban nhap sai !Xin vui long nhap lai.");
            }
        }
        while (true) {
            System.out.println("Gioi tinh : ");
            System.out.println("1. Nam ");
            System.out.println("2. Nu ");
            System.out.printf("Chon : ");
            int choose = presentation.validateInteger();
            if (choose == 1) {
                cus.setGender("Nam");
                break;
            } else if (choose == 2) {
                cus.setGender("Nu");
                break;
            } else {
                System.out.println("Ban nhap sai !\nXin vui long nhap lai!");
            }
        }
        while (true) {
            System.out.printf("Ngay sinh (dd-mm-yyyy): ");
            String date = sc.nextLine();
            date = date.trim();
            if (presentation.validDate(date) == true) {
                date = presentation.dateBirth(date);
                cus.setBirthDate(date);
                break;
            } else {
                System.out.println("Ban nhap sai , xin vui long nhap lai !");
            }
        }

        while (true) {
            String pass;
            while (true) {
                System.out.printf("Mat khau : ");
                pass = customerBl.password();
                if (presentation.validPassword(pass) == true) {
                    break;
                } else {
                    System.out.println(
                            "Mat khau khong hop le !\nMat khau phai chua it nhat mot chu cai viet hoa, mot chu cai viet thuong , mot chu so , tu 8-12 ky tu !");
                }
            }
            System.out.printf("Nhap lai mat khau : ");
            final String pass2 = customerBl.password();
            if (pass.equals(pass2)) {
                cus.setPassword(customerBl.md5(pass));
                break;
            } else {
                System.out.printf("Mat khau khong khop vui long nhap lai !\n");
            }
        }
        if (customerBl.register(cus) == true) {
            login();
        }
    }

    public static void menuCustomer() {
        while (true) {
            final String[] menuMain = { "Sach", "Quản lý tài khoản", "Quản lý hóa đơn", "Gio hang", "Thoát" };
            final int choose = App.menu(menuMain, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    menuBook();
                    break;
                case 2:
                    accountManagement();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuBook() {
        while (true) {
            String[] arr = { "Sach", "Danh muc", "Tim kiem", "Thoat" };
            int choose = App.menu(arr, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    clrscr();
                    List<Book> listBook = bookBl.displayBook();
                    displayBook(listBook);
                    break;
                case 2:
                    while (true) {
                        boolean boo = false;
                        clrscr();
                        bookBl.displayCategory();
                        System.out.println("1. Nhap ma danh muc de xem danh sach sach trong danh muc");
                        System.out.println("2. Quay lai");
                        while (true) {
                            System.out.printf("#Chon : ");
                            int a = presentation.validateInteger();
                            if (a == 1) {
                                searchBookCategory();
                                break;
                            } else if (a == 2) {
                                boo = true;
                                break;
                            } else {
                                System.out.println("Ban nhap sai ! Moi nhap lai .");
                                sc.nextLine();
                            }
                        }
                        if (boo == true) {
                            break;
                        }
                    }
                    break;
                case 3:
                    clrscr();
                    menuSearchBook();
                    break;
                case 4:
                    return;

                default:
                    break;
            }
        }
    }

    public static void displayBook(List<Book> listBook) {

        while (true) {
            clrscr();
            System.out.println(
                    "=============================================================================================================");
            System.out.printf("|%-4s|%-50s|%-30s|%-20s| \n", "Id", "Ten sach", "Tac gia", "Gia (vnd)");
            System.out.println(
                    "=============================================================================================================");
            for (Book rs : listBook) {
                System.out.printf("|%-4d|%-50s|%-30s|%-20s|\n", rs.getBookId(), rs.getTitle(), rs.getAuthor(),
                        rs.getPrice());
            }
            System.out.println(
                    "=============================================================================================================");
            System.out.println("1. Nhap ma sach de xem chi tiet");
            System.out.println("2. Quay lai");
            while (true) {
                System.out.printf("#Chon : ");
                int a = presentation.validateInteger();
                if (a == 1) {
                    detailBook(listBook);
                    break;
                } else if (a == 2) {
                    return;
                } else {
                    System.out.println("Ban nhap sai ! Moi nhap lai .");
                }
            }
        }

    }

    public static void menuSearchBook() {
        while (true) {
            String[] menuSearch = { "Tim kiem theo ten", "Tim kiem theo danh muc", "Tim kiem theo ten va danh muc",
                    "Thoat" };
            int choose = menu(menuSearch, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    clrscr();
                    searchBookName();
                    break;
                case 2:
                    clrscr();
                    bookBl.displayCategory();
                    searchBookCategory();
                    sc.nextLine();
                    break;
                case 3:
                    clrscr();
                    searchBookCategoryAndName();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }

    }

    public static void searchBookName() {
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
        if (listBook.isEmpty() == true) {
            System.out.println("Khong tim thay sach");
            sc.nextLine();
        } else {
            displayBook(listBook);
        }
    }

    public static void searchBookCategory() {

        int id;
        while (true) {
            System.out.printf("Nhap ma danh muc : ");
            id = sc.nextInt();
            if (id > 0 && id < 7)
                break;
        }
        List<Book> listBook = bookBl.searchBookCategory(id);
        if (listBook.isEmpty() == true) {
            System.out.println("Khong tim thay sach");
            sc.nextLine();
        } else {
            displayBook(listBook);
        }
    }

    public static void searchBookCategoryAndName() {
        bookBl.displayCategory();
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
        if (listBook.isEmpty() == true) {
            System.out.println("Khong tim thay sach");
            sc.nextLine();
        } else {
            displayBook(listBook);
        }
    }

    public static void detailBook(List<Book> listBook) {
        System.out.printf("Nhap ma sach : ");
        int id = sc.nextInt();
        clrscr();
        System.out.println("Thong tin sach ma : " + id);
        boolean boo = false;
        Book book = new Book();
        for (int i = 0; i < listBook.size(); i++) {
            if (id == listBook.get(i).getBookId()) {
                book = listBook.get(i);
                boo = true;
                break;
            }
        }
        System.out.println("------------------------------------------------------");
        if (boo == false) {
            System.out.println("Ma sach khong dung !\nNhan phim bat ky de quay lai .");
        } else {
            System.out.println("Ten sach : " + book.getTitle());
            System.out.println("Tac gia : " + book.getAuthor());
            System.out.println("Nha xuat ban : " + book.getPublishingCompanyName());
            System.out.println("Gia : " + book.getPrice());
            System.out.println("So luong : " + book.getQuantity());
            System.out.println("Mo ta : " + book.getDetail());
        }
        sc.nextLine();
        sc.nextLine();
    }

    public static void accountManagement() {
        while (true) {
            String[] arr = { "Thong tin ca nhan", "Cap nhat thong tin", "Dia chi nhan hang", "Dang xuat", "Thoat" };
            int choose = App.menu(arr, "Cap nhat thong tin");
            switch (choose) {
                case 1:
                    clrscr();
                    detailCustomer(idCustomer);
                    break;
                case 2:
                    clrscr();
                    updateCustomer(idCustomer);
                    break;
                case 3:
                    break;
                case 4:
                    mainMenu();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public static void detailCustomer(int id) {
        Customer customer = customerBl.detailCustomer(id);
        System.out.println("Thong tin tai khoan ca nhan");
        System.out.println("-----------------------------------------------");
        System.out.println("Ten           : " + customer.getName());
        System.out.println("So dien thoai : " + customer.getPhone());
        System.out.println("Email         : " + customer.getEmail());
        System.out.println("Gio tinh      : " + customer.getGender());
        System.out.println("Ngay sinh     : " + customer.getBirthDate());
        System.out.println("-----------------------------------------------");
        sc.nextLine();
    }

    public static void updateCustomer(int id) {
        System.out.println("Cap nhat thong tin");
        while (true) {
            String[] arr = { "Ten", "So dien thoai", "Email", "Gioi tinh", "Ngay sinh", "Mat khau", "Dia chi",
                    "Thoat" };
            int choose = App.menu(arr, "Chào mừng bạn đến với Bookstore");
            switch (choose) {
                case 1:
                    updateCustomerName(idCustomer);
                    break;
                case 2:
                    updateCustomerPhone(idCustomer);
                    break;
                case 3:
                    updateCustomerEmail(idCustomer);
                    break;
                case 4:
                    updateCustomerGender(idCustomer);
                    break;
                case 5:
                    updateCustomerBirth(idCustomer);
                    break;
                case 6:
                    updateCustomerPass(idCustomer);
                    break;
                case 7:
                    break;
                case 8:
                    return;
                default:
                    break;
            }
        }
    }

    public static void updateCustomerName(int id) {
        String n;
        while (true) {
            System.out.printf("Ten khach hang : ");
            n = sc.nextLine();
            n = n.trim();
            if (presentation.validName(n) == true && n.isEmpty() == false) {
                break;
            } else {
                System.out.println("Ban nhap sai !\nXin vui long nhap lai.");
            }
        }
        if (customerBl.updateCustomerName(id, n) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }

    public static void updateCustomerPhone(int id) {
        List<Customer> listCustomer = customerBl.listCustomer();
        String p;
        while (true) {
            System.out.printf("So dien thoai : ");
            p = sc.nextLine();
            p = p.trim();
            for (int i = 0; i < 0; i++) {
                if (p.equals(listCustomer.get(i).getPhone()) && idCustomer != listCustomer.get(i).getIdCustomer()) {
                    System.out.printf("So dien thoai da ton tai ban co muon nhap lai ?(C/K) : ");
                    final String ck = presentation.yesOrNo();
                    if (ck.equalsIgnoreCase("c")) {
                    } else
                        break;
                }
            }
            if (presentation.validPhone(p) == true) {
                break;
            } else {
                System.out.println("Ban nhap sai !Xin vui long nhap lai.");
            }
        }
        if (customerBl.updateCustomerPhone(id, p) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }

    public static void updateCustomerEmail(int id) {
        List<Customer> listCustomer = customerBl.listCustomer();
        String e;
        while (true) {
            System.out.printf("Email : ");
            e = sc.nextLine();
            e = e.trim();
            for (int i = 0; i < 0; i++) {
                if (e.equals(listCustomer.get(i).getEmail()) && idCustomer != listCustomer.get(i).getIdCustomer()) {
                    System.out.printf("Email da ton tai ban co muon nhap lai ?(C/K) : ");
                    final String ck = presentation.yesOrNo();
                    if (ck.equalsIgnoreCase("c")) {
                    } else
                        break;
                }
            }
            if (presentation.validEmail(e) == true) {
                break;
            } else {
                System.out.println("Ban nhap sai !Xin vui long nhap lai.");
            }
        }
        if (customerBl.updateCustomerEmail(id, e) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }

    public static void updateCustomerGender(int id) {
        String n;
        while (true) {
            System.out.println("Gioi tinh : ");
            System.out.println("1. Nam ");
            System.out.println("2. Nu ");
            System.out.printf("Chon : ");
            int choose = presentation.validateInteger();
            if (choose == 1) {
                n = "Nam";
                break;
            } else if (choose == 2) {
                n = "Nu";
                break;
            } else {
                System.out.println("Ban nhap sai !\nXin vui long nhap lai!");
            }
        }
        if (customerBl.updateCustomerGender(id, n) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }

    public static void updateCustomerBirth(int id) {
        String date;
        while (true) {
            System.out.printf("Ngay sinh (dd-mm-yyyy): ");
            date = sc.nextLine();
            date = date.trim();
            if (presentation.validDate(date) == true) {
                date = presentation.dateBirth(date);
                break;
            } else {
                System.out.println("Ban nhap sai , xin vui long nhap lai !");
            }
        }
        if (customerBl.updateCustomerBirth(id, date) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }

    public static void updateCustomerPass(int id) {
        String pass;
        while (true) {
            while (true) {
                System.out.printf("Mat khau : ");
                pass = customerBl.password();
                if (presentation.validPassword(pass) == true) {
                    break;
                } else {
                    System.out.println(
                            "Mat khau khong hop le !\nMat khau phai chua it nhat mot chu cai viet hoa, mot chu cai viet thuong , mot chu so , tu 8-12 ky tu !");
                }
            }
            System.out.printf("Nhap lai mat khau : ");
            final String pass2 = customerBl.password();
            if (pass.equals(pass2)) {
                pass = customerBl.md5(pass);
                break;
            } else {
                System.out.printf("Mat khau khong khop vui long nhap lai !\n");
            }
        }
        if (customerBl.updateCustomerPass(id, pass) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
        }
    }
}
