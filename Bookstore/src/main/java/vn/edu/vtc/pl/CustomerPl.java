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
      String my  = "My account : "+ nameCustomer(id);
      String[] arr = { "Personal information", "Delivery address", "Log out", "Come back" };
      int choose = app.menu(arr, my);
      switch (choose) {
        case 1:
          updateCustomer(id);
          break;
        case 2:
          app.clrscr();
        addressUpdate(id); 
          break;
        case 3:
          app.idCustomer = 0;
          app.mainMenu();
          break;
        case 4:
          return;
        default:
          break;
      }
    }
  }

  public boolean login() {
    while (true) {
      app.clrscr();
      System.out.println("                Login");
      System.out.println("-------------------------------------------");
      String ep;
      while (true) {
        System.out.printf("Email/Phone number : ");
        ep = sc.nextLine();
        if (presentation.validEmail(ep) == true || presentation.validPhone(ep) == true) {
          break;
        } else {
          System.out.printf("You entered incorrectly, please re-enter !\n");
        }
      }
      System.out.printf("Password : ");
      String pass = customerBl.password();
      pass = customerBl.encodeMd5(pass);
      if (customerBl.login(ep, pass) == true) {
        System.out.println("Logged in successfully");
        sc.nextLine();
        return true;
      
      }
      System.out.print("Email/phone number or password is incorrect\nDo you want to re-enter (Y/N) :");
      final String ck = presentation.yesOrNo();
      if (ck.equalsIgnoreCase("n")) {
        return false;
      }
    }
  }

  public String enterName() {
    while (true) {
      System.out.printf("Name : ");
      String n = sc.nextLine();
      n = n.trim();
      if (presentation.validName(n) == true && n.isEmpty() == false) {
        return n;
      } else {
        System.out.println("You entered wrong! Please re-enter");
      }
    }
  }

  public String enterPhone() {
    while (true) {
      System.out.printf("Phone number : ");
      String p = sc.nextLine();
      p = p.trim();
      if (presentation.validPhone(p) == true && p.isEmpty() == false) {
        return p;
      } else {
        System.out.println("You entered wrong! Please re-enter.");
      }
    }
  }

  public String enterGender() {
    while (true) {
      System.out.println("Gender : ");
      System.out.println("1. Male ");
      System.out.println("2. Female ");
      System.out.printf("Choose : ");
      int choose = presentation.validateInteger();
      if (choose == 1) {
        return "Nam";
      } else if (choose == 2) {
        return "Nu";
      } else {
        System.out.println("You entered wrong! Please re-enter.");
      }
    }
  }

  public String enterPassword() {
    while (true) {
      String pass;
      while (true) {
        System.out.printf("Password : ");
        pass = customerBl.password();
        if (presentation.validPassword(pass) == true) {
          break;
        } else {
          System.out.println(
              "Invalid password ! \nPassword must contain at least one uppercase letter, one lowercase letter, one number, between 8-12 characters !");
        }
      }
      System.out.printf("Enter the password : ");
      final String pass2 = customerBl.password();
      if (pass.equals(pass2)) {

        return customerBl.encodeMd5(pass);
      } else {
        System.out.printf("Password mismatch, please re-enter !\n");
      }
    }
  }

  public String enterBirthDate() {
    while (true) {
      System.out.printf("Date of birth (dd-mm-yyyy): ");
      String date = sc.nextLine();
      date = date.trim();
      if (presentation.validDate(date) == true) {
        date = presentation.dateBirth(date);
        return date;
      } else {
        System.out.println("You entered incorrectly, please re-enter !");
      }
    }
  }

  public void register() {
    app.clrscr();
    System.out.println("                    Register");
    System.out.println("--------------------------------------------------");
    List<Customer> listCustomer = customerBl.listCustomer();
    Customer cus = new Customer();
    String name = enterName();
    cus.setName(name);
    while (true) {
      boolean boo = false;
      System.out.printf("Phone number : ");
      String p = sc.nextLine();
      p = p.trim();
      if (presentation.validPhone(p) == true && p != null) {
        for (int i = 0; i < listCustomer.size(); i++) {
          if (p.equals(listCustomer.get(i).getPhone())) {
            boo = true;
            System.out.printf("Phone number already exists you want to login?(Y/N) : ");
            String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("y")) {
              login();
            }
          }
        }
        if (boo == false) {
          cus.setPhone(p);
          break;
        }
      } else {
        System.out.println("You entered incorrectly, please re-enter !");
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
            System.out.printf("Email already exists, you want to login ?(Y/N) : ");
            final String ck = presentation.yesOrNo();
            if (ck.equalsIgnoreCase("y")) {
              login();
            }
          }
        }
        if (boo == false) {
          cus.setEmail(e);
          break;
        }
      } else {
        System.out.println("You entered incorrectly, please re-enter !");
      }
    }
    String gender = enterGender();
    cus.setGender(gender);
    String date = enterBirthDate();
    cus.setBirthDate(date);
    String pass = enterPassword();
    cus.setPassword(pass);
    if (customerBl.register(cus) == true) {
      System.out.println("Sign Up Success.");
      sc.nextLine();
      if(login() == true){
        app.menuCustomer();
      }
    } else {
      System.out.println("Registration failed.");
      sc.nextLine();
    }
  }

  public String nameCustomer(int id) {
    Customer customer = customerBl.detailCustomer(id);
    return customer.getName();
  }

  public void updateCustomer(int id) {
    while (true) {
      app.clrscr();
      Customer customer = customerBl.detailCustomer(id);
    System.out.println("Personal account information");
    System.out.println("-----------------------------------------------");
    System.out.println("Name           : " + customer.getName());
    System.out.println("Phone number   : " + customer.getPhone());
    System.out.println("Email          : " + customer.getEmail());
    System.out.println("Gender         : " + customer.getGender());
    System.out.println("Date of birth  : " + presentation.dateBirth1(customer.getBirthDate()));
    System.out.println("-----------------------------------------------");
      String[] arr = { "Name", "Phone number", "Email", "Gender", "Date of birth", "Password", "Come back" };
      int choose = app.menu(arr, "Update information");
      switch (choose) {
        case 1:
          String n = enterName();
          if (customerBl.updateCustomerName(id, n) == true) {
            System.out.println("Update successful.");
            sc.nextLine();
          }
          break;
        case 2:
          List<Customer> listCustomer = customerBl.listCustomer();
          String p;
          while (true) {
            System.out.printf("Phone number : ");
            p = sc.nextLine();
            p = p.trim();
            for (int i = 0; i < 0; i++) {
              if (p.equals(listCustomer.get(i).getPhone()) && id != listCustomer.get(i).getIdCustomer()) {
                System.out.printf("Phone number already exists you want to re-enter ?(Y/N) : ");
                final String ck = presentation.yesOrNo();
                if (ck.equalsIgnoreCase("y")) {
                } else
                  break;
              }
            }
            if (presentation.validPhone(p) == true) {
              break;
            } else {
              System.out.println("You entered wrong! Please re-enter.");
            }
          }
          if (customerBl.updateCustomerPhone(id, p) == true) {
            System.out.println("Update successful.");
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
                System.out.printf("Email already exists you want to re-enter ?(Y/N) : ");
                final String ck = presentation.yesOrNo();
                if (ck.equalsIgnoreCase("y")) {
                } else
                  break;
              }
            }
            if (presentation.validEmail(e) == true) {
              break;
            } else {
              System.out.println("You entered wrong! Please re-enter.");
            }
          }
          if (customerBl.updateCustomerEmail(id, e) == true) {
            System.out.println("Update successful.");
            sc.nextLine();
          }
          break;
        case 4:
          String gender = enterGender();
          if (customerBl.updateCustomerGender(id, gender) == true) {
            System.out.println("Update successful.");
            sc.nextLine();
          }
          break;
        case 5:
        String date = enterBirthDate();
        if (customerBl.updateCustomerBirth(id, date) == true) {
          System.out.println("Update successful.");
          sc.nextLine();
        }
          break;
        case 6:
        String pass = enterPassword();
        if (customerBl.updateCustomerPass(id, pass) == true) {
          System.out.println("Update successful.");
          sc.nextLine();
        }
          break;
        
        case 7:
          return;
        default:
          break;
      }
    }
  }

  public void addressUpdate(int id) {
    while (true) {
      app.clrscr();
      System.out.println(customerBl.viewAddressList(id));
      String[] arr = { "Add the address", "Correct address", "Delete the address", "Change the default address", "Come back" };
      int choose = app.menu(arr, "Update address");
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
    System.out.println("Add the address");
    String n = enterName();
    String p = enterPhone();
    String c = checkStringNull("City : ");
    String d = checkStringNull("District : ");
    String add = checkStringNull("Commune : ");
    if (customerBl.insertAddress(n, p, c, d, add, id) == true) {
      System.out.println("Address added successfully");
      sc.nextLine();
    }
  }

  public void repairAddress(int id) {
    app.clrscr();
    if (customerBl.viewAddressList(id).isEmpty() == false) {
      System.out.println(customerBl.viewAddressList(id));
      if(customerBl.viewAddressList(id).equalsIgnoreCase("No receiving address !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Enter the id of the address you want to edit : ");
      while (true) {
        app.clrscr();
        System.out.println("1. Recipient's name");
        System.out.println("2. Phone number");
        System.out.println("3. Delivery address");
        System.out.println("4. Come back");
        System.out.printf("Choose : ");
        int choose1 = presentation.validateInteger();
        if (choose1 == 1) {
          app.clrscr();
          String n = enterName();
          if (customerBl.updateNameAddress(choose, n) == true) {
            System.out.println("Update successful.  ");
            sc.nextLine();
          }
        } else if (choose1 == 2) {
          app.clrscr();
          String p = enterPhone();
          if (customerBl.updatePhoneAddress(choose, p) == true) {
            System.out.println("Update successful.  ");
            sc.nextLine();
          }
        } else if (choose1 == 3) {
          app.clrscr();
          String c = checkStringNull("City : ");
          String d = checkStringNull("District : ");
          String add = checkStringNull("Commune : ");
          customerBl.updateAddress(choose, c, d, add);
          System.out.println("Update successful.");
          sc.nextLine();
        } else if (choose1 == 4) {
          return;
        } else {
          System.out.println("You choose wrong! Please choose again.");
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
      if(customerBl.viewAddressList(id).equalsIgnoreCase("No receiving address !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Select the address id you want to delete : ");
      System.out.printf("Are you sure you want to delete this address (Y/N)? : ");
      String ck = presentation.yesOrNo();
      if (ck.equalsIgnoreCase("y")) {
        if (customerBl.updateStatusAddress(choose) == true) {
          System.out.println("Address deleted successfully");
          sc.nextLine();
        }
      }
    }
  }

  public void defaultAddress(int id) {
    app.clrscr();
    if (customerBl.viewAddressList(id).isEmpty() == false) {
      System.out.println(customerBl.viewAddressList(id));
      if(customerBl.viewAddressList(id).equalsIgnoreCase("No receiving address !")){
        sc.nextLine();
        return;
      }
      int choose = checkIdAddress(id, "Select the address id you want to set as default : ");
      String str = "Mac dinh";
      if (customerBl.searchAddress(id) == true) {
        if (customerBl.updateDefaultAddress(choose, str) == true) {
          System.out.println("Default address has been successfully set .");
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
        System.out.println("You entered wrong! Please enter again.");
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
        System.out.println("You entered wrong! Please enter again.");
      }
    }
  }
}
