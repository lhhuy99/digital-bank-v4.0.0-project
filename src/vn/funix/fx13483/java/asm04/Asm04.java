package vn.funix.fx13483.java.asm04;

import vn.funix.fx13483.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.fx13483.java.asm04.model.DigitalBank;

import java.util.Scanner;

public class Asm04 {
    private static final String AUTHOR = "FX13483";
    private static final String VERSION = "@v4.0.0";
    private static final DigitalBank activeBank = new DigitalBank();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        controlBankApp();
    }

    public static void controlBankApp() {
        displayMenu();
        selectFunction();
    }

    public static void displayMenu() {
        String border = "+----------+------------------+----------+";
        System.out.println(border);
        System.out.println("| NGAN HANG DIEN TU | " + AUTHOR + VERSION + "          |");
        System.out.println(border);
        System.out.println("| 1. Xem danh sach khach hang");
        System.out.println("| 2. Nhap danh sach khach hang");
        System.out.println("| 3. Them tai khoan ATM");
        System.out.println("| 4. Chuyen tien");
        System.out.println("| 5. Rut tien");
        System.out.println("| 6. Tra cuu lich su giao dich");
        System.out.println("| 0. Thoat");
        System.out.println(border);
    }

    //Nhap so chuc nang 0 -> 6
    public static int enterNumberFunction() {
        String strUseToParseInt;
        int selectedNumber = 6;
        boolean checkBoolean = true;
        while (checkBoolean) {
            try {
                System.out.print("Chuc nang: ");
                strUseToParseInt = sc.nextLine();
                selectedNumber = Integer.parseInt(strUseToParseInt);
                switch (selectedNumber) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        checkBoolean = false;
                        break;
                    default:
                        System.out.println("Vui long nhap dung so chuc nang");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap dung so chuc nang");
            }
        }
        return selectedNumber;
    }

    //Chon va thuc thi cac chuc nang 0 -> 6
    public static void selectFunction() {
        switch (enterNumberFunction()) {
            case 1:
                activeBank.showCustomers();
                returnMenu();
                break;
            case 2:
                System.out.println("Nhap duong dan den tep:");
                try {
                    activeBank.addCustomer(sc.nextLine());
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                }
                returnMenu();
                break;
            case 3:
                System.out.println("Nhap ma so khach hang: ");
                try {
                    activeBank.addSavingAccount(sc, sc.nextLine());
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                }
                returnMenu();
                break;
            case 4:
                System.out.println("Nhap ma so khach hang: ");
                try {
                    activeBank.tranfers(sc, sc.nextLine());
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                }
                returnMenu();
                break;
            case 5:
                System.out.println("Nhap ma so khach hang: ");
                try {
                    activeBank.withdraw(sc, sc.nextLine());
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                }
                returnMenu();
                break;
            case 6:
                System.out.println("Nhap ma so khach hang: ");
                try {
                    activeBank.displayTransaction(sc.nextLine());
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                }
                returnMenu();
                break;
            case 0:
                System.exit(0);
        }
    }

    //Thoat ve menu chinh
    public static void returnMenu() {
        System.out.println("Nhap OK de quay ve menu chinh");
        if (sc.nextLine().equalsIgnoreCase("ok")) {
            controlBankApp();
        } else {
            returnMenu();
        }
    }
}