package vn.funix.fx13483.java.asm04.model;

import vn.funix.fx13483.java.asm04.common.common;
import vn.funix.fx13483.java.asm04.dao.TransactionDao;
import vn.funix.fx13483.java.asm04.file.TransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account implements Serializable {
    private String accountNumber;
    private double balance;
    private static final long serialVersionUID = 1L;
    private String customerId;
    private static final Scanner sc = new Scanner(System.in);

    public Account() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        boolean checkBoolean = true;
        while (checkBoolean) {
            try {
                long checkException = Long.parseLong(accountNumber);
                if (accountNumber.length() == 6) {
                    this.accountNumber = accountNumber;
                    checkBoolean = false;
                } else {
                    System.out.println("So tai khoan phai gom 6 chu so. Xin vui long nhap lai");
                    accountNumber = sc.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("So tai khoan phai gom 6 chu so. Xin vui long nhap lai");
                accountNumber = sc.nextLine();
            }
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        boolean checkBoolean = true;
        while (checkBoolean) {
            if (balance >= 50000) {
                this.balance = balance;
                checkBoolean = false;
            } else {
                System.out.println("So du khong duoc nho hon 50000. Xin vui long nhap lai");
                balance = enterAmount();
            }
        }
    }

    //Nhap va kiem tra so tien co hop le
    //Xuat ra gia tri hop le se duoc truyen vao tham so o phuong thuc setBalance
    public double enterAmount() {
        boolean checkBoolean = true;
        while (checkBoolean) {
            try {
                String balanceAmountString = sc.nextLine();
                double balanceAmount = Double.parseDouble(balanceAmountString);
                return balanceAmount;
            } catch (NumberFormatException e) {
                System.out.println("So du phai la so. Xin vui long nhap lai");
            }
        }
        return -1;
    }

    //Kiem tra tai khoan co la premium
    public boolean isPremiumAccount() {
        return balance >= 10000000;
    }

    //Lay ra cac giao dich thuoc account nay
    public List<Transaction> getTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        TransactionDao.list()
                .stream()
                .filter(transaction -> transaction.getAccountNumber().equals(getAccountNumber()))
                .forEach(transactionList::add);
        return transactionList;
    }

    //Hien thi danh sach transaction
    public void displayTransactionList() {
        for (Transaction transaction : getTransactions()) {
            if (transaction.getType().equals(TransactionType.WITHDRAW)) {
                System.out.printf("%4s%8s | %8s%18s | %20s | %s%n", "[GD]", transaction.getAccountNumber(), transaction.getType(), common.formatBalance(transaction.getAmount() * -1), transaction.getTime(), transaction.getId());
            } else {
                System.out.printf("%4s%8s | %8s%18s | %20s | %s%n", "[GD]", transaction.getAccountNumber(), transaction.getType(), common.formatBalance(transaction.getAmount()), transaction.getTime(), transaction.getId());
            }
        }
    }

    //Tao ra them mot giao dich cho account va cap nhat so du tai khoan
    public void createTransaction(double amount, String time, boolean status, TransactionType type) {
        //Them giao dich vao trong file
        Transaction transaction = new Transaction(getAccountNumber(), amount, time, status, type);
        TransactionDao.update(transaction);

        //Cap nhat so du tai khoan
        if (type.equals(TransactionType.DEPOSIT)) {
        } else if (type.equals(TransactionType.WITHDRAW)) {
            setBalance(getBalance() - amount);

        } else {    //TransactionType.TRANFERS
            setBalance(getBalance() + amount);
        }
    }

    //Them tai khoan moi vao danh sach
    public void input(Scanner scanner) {
        //Nhap so tai khoan va so tien ban dau
        System.out.println("Nhap so tai khoan gom 6 chu so: ");
        setAccountNumber(scanner.nextLine());
        System.out.println("Nhap so du tai khoan >= 50000d: ");
        setBalance(enterAmount());

        //Tao ra mot giao dich them tien va thay doi so du tai khoan
        createTransaction(getBalance(), common.getDateTime(), true, TransactionType.DEPOSIT);
    }

}
