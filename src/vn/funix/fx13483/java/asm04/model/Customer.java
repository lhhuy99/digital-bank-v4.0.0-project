package vn.funix.fx13483.java.asm04.model;

import vn.funix.fx13483.java.asm04.common.CustomerIdValidator;
import vn.funix.fx13483.java.asm04.common.common;
import vn.funix.fx13483.java.asm04.dao.AccountDao;
import vn.funix.fx13483.java.asm04.dao.CustomerDao;
import vn.funix.fx13483.java.asm04.exception.CustomerIdNotValidException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements Serializable {
    private String name;
    private String customerId;
    private static final long serialVersionUID = 1L;
    private static final Scanner sc = new Scanner(System.in);

    public Customer(List<String> values) {
        this(values.get(0), values.get(1));
    }

    public Customer(String customerId, String name) throws CustomerIdNotValidException {
        this.name = name;
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            this.customerId = customerId;
        } else {
            throw new CustomerIdNotValidException("Can cuoc cong dan khong hop le");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        boolean checkBoolean = true;
        while (checkBoolean) {
            try {
                long checkException = Long.parseLong(customerId);
                if (customerId.length() == 12) {
                    this.customerId = customerId;
                    checkBoolean = false;
                } else {
                    System.out.println("So CCCD khong hop le. Vui long nhap lai");
                    customerId = sc.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("So CCCD khong hop le. Vui long nhap lai");
                customerId = sc.nextLine();
            }
        }
    }

    //Lay ra cac nhung account co customerId bang customerId hien tai
    public List<Account> getAccounts() {
        List<Account> accountList = new ArrayList<>();
        AccountDao.list()
                .stream()
                .filter(account -> account.getCustomerId().equals(customerId))
                .forEach(accountList::add);
        return accountList;
    }

    //Hien thi thong tin chi tiet khach hang va cac tai khoan cua khach hang do
    public void displayInformation() {
        System.out.printf("%12s | %-26s |  %9s |   %20s%n", getCustomerId(), getName(), isPremiumAccount(), common.formatBalance(getBalance()));

        int orderAccount = 1;
        for (Account account : getAccounts()) {
            System.out.printf("%d%11s | %18s%8s |%36s%n", orderAccount, account.getAccountNumber(), " ", getAccountType(account), common.formatBalance(account.getBalance()));
            orderAccount++;
        }
    }

    //Kiem tra la tai khoan Savings hay la tai khoan khac
    public String getAccountType(Account account) {
        if (account instanceof SavingAccount) {
            return "SAVINGS";
        }
        return "UNKNOWN";
    }

    //Lay ra account tu trong danh sach
    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    //Hien thi thong tin customer, cac tai khoan va cac giao dich cua khach hang hien tai
    public void displayTransactionInformation() {
        for (Account account : getAccounts()) {
            account.displayTransactionList();
        }
    }

    public void withdraw(Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account = null;
            double amount;

            do {
                System.out.println("Nhap so tai khoan: ");
                account = getAccountByAccountNumber(accounts, scanner.nextLine());
                if (account == null) {
                    System.out.println("Khong tim thay tai khoan");
                }
            } while (account == null);

            do {
                System.out.println("Nhap so tien rut: ");
                try {
                    amount = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("So tien nhap khong hop le");
                    amount = -1;
                }
            } while (amount <= 0);

            if (account instanceof SavingAccount) {
                ((SavingAccount) account).withdraw(amount);
                AccountDao.update(account);
            }
        } else {
            System.out.println("Khach hang khong co tai khoan nao, thao tac khong thanh cong");
        }
    }

    public void tranfers(Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            Account receivedAccount;
            double amount;

            do {
                System.out.println("Nhap so tai khoan: ");
                account = getAccountByAccountNumber(accounts, scanner.nextLine());
                if (account == null) {
                    System.out.println("Khong tim thay tai khoan");
                }
            } while (account == null);

            do {
                System.out.println("Nhap so tai khoan nhan (exit de thoat): ");
                String accountNumber = scanner.nextLine();
                if (accountNumber.equalsIgnoreCase("exit")) {
                    return;
                }
                receivedAccount = getAccountByAccountNumber(AccountDao.list(), accountNumber);
                if (receivedAccount == null) {
                    System.out.println("Khong tim thay tai khoan");
                } else if (receivedAccount.getAccountNumber().equals(account.getAccountNumber())) {
                    System.out.println("Tai khoan nhan trung voi tai khoan gui");
                    receivedAccount = null;
                }

            } while (receivedAccount == null);

            Customer customer = getCustomerById(CustomerDao.list(), receivedAccount.getCustomerId());
            System.out.println("Gui tien den tai khoan: " + receivedAccount.getAccountNumber() + " | " + customer.getName());

            do {
                System.out.println("Nhap so tien chuyen: ");
                try {
                    amount = Double.parseDouble(scanner.nextLine());
                    if (amount < 50000 || amount > account.getBalance()) {
                        System.out.println("So tien khong hop le");
                        amount = -1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("So tien nhap khong hop le");
                    amount = -1;
                }
            } while (amount <= 0);

            System.out.println("Xac nhan thuc hien chuyen " + common.formatBalance(amount) + " tu tai khoan [" + account.getAccountNumber() + "] den tai khoan [" + receivedAccount.getAccountNumber() + "] (Y/N):");
            if (scanner.nextLine().equalsIgnoreCase("y") || scanner.nextLine().equalsIgnoreCase("yes")) {
                if (account instanceof SavingAccount) {
                    ((SavingAccount) account).tranfers(receivedAccount, amount);
                    AccountDao.update(account);
                    AccountDao.update(receivedAccount);
                }
            }
        } else {
            System.out.println("Khach hang khong co tai khoan nao, thao tac khong thanh cong");
        }
    }

    public String isPremiumAccount() {
        for (Account account : getAccounts()) {
            if (account.isPremiumAccount()) {
                return "Premium";
            }
        }
        return "Normal";
    }

    //Ham tra ve tong so du cua tat ca tai khoan khach hang co
    public double getBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public Customer getCustomerById(List<Customer> customerList, String customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}
