package vn.edu.vtc;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import vn.edu.vtc.bl.BookBl;
import vn.edu.vtc.bl.CustomerBl;

public class App 
{
    public static void main( final String[] args )
    {
        Scanner sc = new Scanner(System.in);
        while(true){
        String[] menuMain = {"Sản phẩm", "Đăng nhập", "Đăng ký", "Thoát"};
        int choose = menu(menuMain, "Chào mừng bạn đến với Bookstore");
        switch (choose) {
                case 1:
                    
                    break;
                case 2:
                    // clrscr();
                    // CustomerBl.login();
                    break;
                case 3:
                
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        
    }

    public static int menu(final String arr[], final String title){
        int num;
        clrscr();
        System.out.println("=======================================================");
        System.out.printf("||         %-41s ||\n",title);
        System.out.println("-------------------------------------------------------");
        for(int i= 0; i < arr.length; i++){
           System.out.printf("|| %-1d, %-46s ||\n", i+1, arr[i]);
        }
        System.out.println("=======================================================");
        System.out.printf("#Chon : ");
        while(true){
        num = BookBl.validateInteger();
        if(num >= 0 && num <= arr.length){
          break;
        }
        else{
          System.out.printf("Ban nhap sai !\nXin vui long nhap lại : ");
        }
      }
      return num;
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
}


