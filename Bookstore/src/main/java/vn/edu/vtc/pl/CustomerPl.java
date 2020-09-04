package vn.edu.vtc.pl;

import java.util.List;
import java.util.Scanner;
import vn.edu.vtc.App;
import vn.edu.vtc.bl.CustomerBl;
import vn.edu.vtc.bl.Presentation;
import vn.edu.vtc.persistance.Customer;

public class CustomerPl {
  Presentation presentation = new Presentation();
  CustomerBl customerBl = new CustomerBl();
  Scanner sc = new Scanner(System.in);
  App app = new App();

  public void accountManagement(int id) {
    while (true) {
      app.clrscr();
      String[] arr = { "Thong tin ca nhan", "Cap nhat thong tin", "Dia chi nhan hang", "Dang xuat", "Thoat" };
      int choose = app.menu(arr, "Cap nhat thong tin");
      switch (choose) {
        case 1:
          app.clrscr();
          detailCustomer(id);
          break;
        case 2:
          updateCustomer(id);
          break;
        case 3:
          app.clrscr();
          System.out.println(customerBl.viewAddressList(id));
          sc.nextLine();
          break;
        case 4:
          app.idCustomer = 0;
          app.mainMenu();
          break;
        case 5:
          return;
        default:
          break;
      }
    }
  }

  public boolean login() {
    while (true) {
      app.clrscr();
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
      pass = customerBl.encodeMd5(pass);
      if (customerBl.login(ep, pass) == true) {
        System.out.println("Dang nhap thanh cong");
        sc.nextLine();
        return true;
      
      }
      System.out.print("Email/phone hoac password khong dung\nBan co muon nhap lai (C/K) :");
      final String ck = presentation.yesOrNo();
      if (ck.equalsIgnoreCase("k")) {
        return false;
      }
    }
  }

  public String enterName() {
    while (true) {
      System.out.printf("Ten khach hang : ");
      String n = sc.nextLine();
      n = n.trim();
      if (presentation.validName(n) == true && n.isEmpty() == false) {
        return n;
      } else {
        System.out.println("Ban nhap sai !\nXin vui long nhap lai.");
      }
    }
  }

  public String enterPhone() {
    while (true) {
      System.out.printf("So dien thoai : ");
      String p = sc.nextLine();
      p = p.trim();
      if (presentation.validPhone(p) == true && p.isEmpty() == false) {
        return p;
      } else {
        System.out.println("Ban nhap sai !Xin vui long nhap lai.");
      }
    }
  }

  public String enterGender() {
    while (true) {
      System.out.println("Gioi tinh : ");
      System.out.println("1. Nam ");
      System.out.println("2. Nu ");
      System.out.printf("Chon : ");
      int choose = presentation.validateInteger();
      if (choose == 1) {
        return "Nam";
      } else if (choose == 2) {
        return "Nu";
      } else {
        System.out.println("Ban nhap sai !\nXin vui long nhap lai!");
      }
    }
  }

  public String enterPassword() {
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

        return customerBl.encodeMd5(pass);
      } else {
        System.out.printf("Mat khau khong khop vui long nhap lai !\n");
      }
    }
  }

  public String enterBirthDate() {
    while (true) {
      System.out.printf("Ngay sinh (dd-mm-yyyy): ");
      String date = sc.nextLine();
      date = date.trim();
      if (presentation.validDate(date) == true) {
        date = presentation.dateBirth(date);
        return date;
      } else {
        System.out.println("Ban nhap sai , xin vui long nhap lai !");
      }
    }
  }

  public void register() {
    app.clrscr();
    System.out.println("                    Dang ky");
    System.out.println("--------------------------------------------------");
    List<Customer> listCustomer = customerBl.listCustomer();
    Customer cus = new Customer();
    String name = enterName();
    cus.setName(name);
    while (true) {
      boolean boo = false;
      System.out.printf("So dien thoai : ");
      String p = sc.nextLine();
      p = p.trim();
      if (presentation.validPhone(p) == true && p != null) {
        for (int i = 0; i < listCustomer.size(); i++) {
          if (p.equals(listCustomer.get(i).getPhone())) {
            boo = true;
            System.out.printf("So dien thoai da ton tai b co muon dang nhap ?(C/K) : ");
            String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("c")) {
              login();
            }
          }
        }
        if (boo == false) {
          cus.setPhone(p);
          break;
        }
      } else {
        System.out.println("Ban nhap sai !Xin vui long nhap lai.");
      }
    }
    while (true) {
      boolean boo = false;
      System.out.printf("Email : ");
      String e = sc.nextLine();
      e = e.trim();

      if (presentation.validEmail(e) == true && e != null) {
        for (int i = 0; i < listCustomer.size(); i++) {
          if (e.equals(listCustomer.get(i).getEmail())) {
            boo = true;
            System.out.printf("Email da ton tai b co muon dang nhap ?(C/K) : ");
            final String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("c")) {
              login();
            }
          }
        }
        if (boo == false) {
          cus.setEmail(e);
          break;
        }
      } else {
        System.out.println("Ban nhap sai !Xin vui long nhap lai.");
      }
    }
    String gender = enterGender();
    cus.setGender(gender);
    String date = enterBirthDate();
    cus.setBirthDate(date);
    String pass = enterPassword();
    cus.setPassword(pass);
    if (customerBl.register(cus) == true) {
      System.out.println("Dang ky thanh cong.");
      sc.nextLine();
      if(login() == true){
        app.menuCustomer();
      }
    } else {
      System.out.println("Dang ky that bai");
      sc.nextLine();
    }
  }

  public String nameCustomer(int id) {
    Customer customer = customerBl.detailCustomer(id);
    return customer.getName();
  }

  public void detailCustomer(int id) {
    Customer customer = customerBl.detailCustomer(id);
    System.out.println("Thong tin tai khoan ca nhan");
    System.out.println("-----------------------------------------------");
    System.out.println("Ten           : " + customer.getName());
    System.out.println("So dien thoai : " + customer.getPhone());
    System.out.println("Email         : " + customer.getEmail());
    System.out.println("Gio tinh      : " + customer.getGender());
    System.out.println("Ngay sinh     : " + presentation.dateBirth1(customer.getBirthDate()));
    System.out.println("-----------------------------------------------");
    sc.nextLine();
  }

  public void updateCustomer(int id) {
    while (true) {
      app.clrscr();
      String[] arr = { "Ten", "So dien thoai", "Email", "Gioi tinh", "Ngay sinh", "Mat khau", "Dia chi", "Thoat" };
      int choose = app.menu(arr, "Cap nhat thong tin");
      switch (choose) {
        case 1:
          String n = enterName();
          if (customerBl.updateCustomerName(id, n) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
          }
          break;
        case 2:
          List<Customer> listCustomer = customerBl.listCustomer();
          String p;
          while (true) {
            System.out.printf("So dien thoai : ");
            p = sc.nextLine();
            p = p.trim();
            for (int i = 0; i < 0; i++) {
              if (p.equals(listCustomer.get(i).getPhone()) && id != listCustomer.get(i).getIdCustomer()) {
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
          break;
        case 3:
          List<Customer> listCustomer1 = customerBl.listCustomer();
          String e;
          while (true) {
            System.out.printf("Email : ");
            e = sc.nextLine();
            e = e.trim();
            for (int i = 0; i < 0; i++) {
              if (e.equals(listCustomer1.get(i).getEmail()) && id != listCustomer1.get(i).getIdCustomer()) {
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
          break;
        case 4:
          String gender = enterGender();
          if (customerBl.updateCustomerGender(id, gender) == true) {
            System.out.println("Cap nhat thanh cong");
            sc.nextLine();
          }
          break;
        case 5:
        String date = enterBirthDate();
        if (customerBl.updateCustomerBirth(id, date) == true) {
          System.out.println("Cap nhat thanh cong");
          sc.nextLine();
        }
          break;
        case 6:
        String pass = enterPassword();
        if (customerBl.updateCustomerPass(id, pass) == true) {
          System.out.println("Cap nhat thanh cong");
          sc.nextLine();
        }
          break;
        case 7:
        addressUpdate(id);
          break;
        case 8:
          return;
        default:
          break;
      }
    }
  }

  public void addressUpdate(int id) {
    while (true) {
      app.clrscr();
      String[] arr = { "Them dia chi", "Sua dia chi", "Xoa dia chi", "Thay doi dia chi mac dinh", "Thoat" };
      int choose = app.menu(arr, "Cap nhat dia chi");
      switch (choose) {
        case 1:
          app.clrscr();
          insertAddress(id);
          break;
        case 2:
          repairAddress(id);
          break;
        case 3:
          deleteAddress(id);
          break;
        case 4:
          defaultAddress(id);
          break;
        case 5:
          return;
        default:
          break;
      }
    }
  }

  public void insertAddress(int id) {
    System.out.println("Them dia chi moi");
    String n = enterName();
    String p = enterPhone();
    String c = checkStringNull("Tinh/Thanh pho : ");
    String d = checkStringNull("Quan/Huyen : ");
    String add = checkStringNull("So nha, ngo/ngach, xa/phuong : ");
    if (customerBl.insertAddress(n, p, c, d, add, id) == true) {
      System.out.println("Them dia chi thanh cong");
      sc.nextLine();
    }
  }

  public void repairAddress(int id) {
    app.clrscr();
    if (customerBl.viewAddressList(id).isEmpty() == false) {
      System.out.println(customerBl.viewAddressList(id));
      if(customerBl.viewAddressList(id).equalsIgnoreCase("Chua co dia chi nhan hang !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Chon ma dia chi muon sua : ");
      while (true) {
        app.clrscr();
        System.out.println("1. Ten nguoi nhan");
        System.out.println("2. So dien thoai");
        System.out.println("3. Dia chi nhan hang");
        System.out.println("4. Thoat");
        System.out.printf("Chon : ");
        int choose1 = presentation.validateInteger();
        if (choose1 == 1) {
          app.clrscr();
          String n = enterName();
          if (customerBl.updateNameAddress(choose, n) == true) {
            System.out.println("Cap nhat thanh cong ! ");
            sc.nextLine();
          }
        } else if (choose1 == 2) {
          app.clrscr();
          String p = enterPhone();
          if (customerBl.updatePhoneAddress(choose, p) == true) {
            System.out.println("Cap nhat thanh cong ! ");
            sc.nextLine();
          }
        } else if (choose1 == 3) {
          app.clrscr();
          String c = checkStringNull("Tinh/Thanh pho : ");
          String d = checkStringNull("Quan/Huyen : ");
          String add = checkStringNull("So nha, ngo/ngach, xa/phuong : ");
          customerBl.updateAddress(choose, c, d, add);
          System.out.println("Cap nhat thanh cong");
          sc.nextLine();
        } else if (choose1 == 4) {
          return;
        } else {
          System.out.println("Ban chon sai !Moi ban chon lai.");
        }
      }
    } else {
      sc.nextLine();
    }
  }

  public void deleteAddress(int id) {
    app.clrscr();
    if (customerBl.viewAddressList(id).isEmpty() == false) {
      System.out.println(customerBl.viewAddressList(id));
      if(customerBl.viewAddressList(id).equalsIgnoreCase("Chua co dia chi nhan hang !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Chon ma dia chi muon xoa : ");
      System.out.printf("Ban co chac chan muon xoa dia chi nay (C/K)? : ");
      String ck = presentation.yesOrNo();
      if (ck.equalsIgnoreCase("c")) {
        if (customerBl.updateStatusAddress(choose) == true) {
          System.out.println("Xoa dia chi thanh cong");
          sc.nextLine();
        }
      }
    }
  }

  public void defaultAddress(int id) {
    app.clrscr();
    if (customerBl.viewAddressList(id).isEmpty() == false) {
      System.out.println(customerBl.viewAddressList(id));
      if(customerBl.viewAddressList(id).equalsIgnoreCase("Chua co dia chi nhan hang !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Chon ma dia chi ban muon dat mac dinh : ");
      String str = "Mac dinh";
      if (customerBl.searchAddress(id) == true) {
        if (customerBl.updateDefaultAddress(choose, str) == true) {
          System.out.println("Dat dia chi mac dinh thanh cong .");
          sc.nextLine();
        }
      }
    } else {
      sc.nextLine();
    }
  }

  public String checkStringNull(String str) {
    String c;
    while (true) {
      System.out.printf(str);
      c = sc.nextLine();
      c = c.trim();
      if (c.isEmpty() == false) {
        return c;
      } else {
        System.out.println("Ban nhap sai !\nXin vui long nhap lai.");
      }
    }
  }

  public int checkIdAddress(int id, String str) {
    while (true) {
      System.out.printf(str);
      int choose = presentation.validateInteger();
      if (customerBl.addressExists(id, choose) == true) {
        return choose;
      } else {
        System.out.println("Ban nhap sai !Moi ban nhap lai .");
      }
    }
  }
}
