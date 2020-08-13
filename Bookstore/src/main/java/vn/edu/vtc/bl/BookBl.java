package vn.edu.vtc.bl;

import java.util.Scanner;

public class BookBl {
  public static Scanner sc = new Scanner(System.in);
  
  public static String yesOrNo() {
    while (true) {
      String y = sc.nextLine();
      if (y.equalsIgnoreCase("k") || y.equalsIgnoreCase("c")) {
          return y;
      } else {
          System.out.print("Ban nha sai !\nMoi nhap lai : ");
      }
    }
  }


  public static Integer validateInteger() {
    int x;
            do {
        try {
            x = Integer.parseInt(sc.nextLine());
            break;

        } catch (Exception e) {
            System.out.print("Sai kieu du lieu!\nMoi nhap lai : ");
        }
    } while (true);
    return x;
  }

  public static int checkInteger() {
    int x;
    while (true) {
        x = validateInteger();
        if (x > 0) {
            break;
        } else {
            System.out.print("So luong phai lon hon 0.\nMoi nhap lai : ");
        }
    }
    return x;
  }

  public static double checkDouble() {
      double x;
      while (true) {
          x = validateDouble();
          if (x >= 0) {
              break;
          } else {
              System.out.print("Gia phai lon hon 0 hoac bang 0 .\nMoi nhap lai : ");
          }
      }
      return x;
  }

  public static double validateDouble() {
    double input = Double.NaN;
    while (true) {
        try {
            input = Double.parseDouble(sc.nextLine());
            break;
        } catch (Exception e) {
            System.out.print("Sai kieu du lieu!\nMoi nhap lai : ");
        }
    }
    return input;
  }
}