package vn.funix.fx13483.java.asm04.model;

import vn.funix.fx13483.java.asm04.common.CustomerIdValidator;
import vn.funix.fx13483.java.asm04.dao.AccountDao;
import vn.funix.fx13483.java.asm04.dao.CustomerDao;
import vn.funix.fx13483.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.fx13483.java.asm04.service.TextFileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {
    public DigitalBank() {
        super();
    }

    //Hien thi danh sach khach hang va cac tai khoan
    public void showCustomers() {
        if (CustomerDao.list().isEmpty()) {
            System.out.println("Chua co khach hang nao trong danh sach");
        } else {
            displayInformation();
        }
    }

    //Them khach hang
    public void addCustomer(String fileName) {
        //Lay du lieu tu file txt
        List<List<String>> customersList = TextFileService.readFile(fileName);

        if (customersList.isEmpty()) {
            System.out.println("Khong tim thay du lieu");
        } else {
            //tao danh sach khach hang de check nguoi dung nhap 2 cai customerId trung nhau
            List<Customer> customers = new ArrayList<>();

            //Chay qua tung list trong list lon cua file txt
            for (List<String> list : customersList) {

                //Khoi tao doi tuong customer va kiem tra dieu kien
                //Thoa dieu kien thi add doi tuong vao list customers
                Customer customer;
                try {
                    customer = new Customer(list);
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                //Check trong file dat va cung trong file txt co khach hang trung customerId hay khong
                if ((isCustomerExisted(CustomerDao.list(), customer)) || (isCustomerExisted(customers, customer))) {
                    System.out.println("Khach hang " + customer.getCustomerId() + " da ton tai, them khach hang khong thanh cong");
                } else {
                    customers.add(customer);
                    CustomerDao.update(customer);
                    System.out.println("Da them khach hang " + customer.getCustomerId() + " vao danh sach khach hang");
                }
            }
        }
    }

    //Phuong thuc rut tien
    public void withdraw(Scanner scanner, String customerId) throws CustomerIdNotValidException {
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            //Lay ra customer co id bang id trong tham so phuong thuc
            Customer customer = getCustomerById(CustomerDao.list(), customerId);
            if (customer == null) {
                System.out.println("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
            } else {
                customer.displayInformation();
                customer.withdraw(scanner);
            }
        } else {
            throw new CustomerIdNotValidException("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
        }
    }

    //Phuong thuc chuyen tien giua 2 tai khoan
    public void tranfers(Scanner scanner, String customerId) throws CustomerIdNotValidException {
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            //Lay ra customer co id bang id trong tham so phuong thuc
            Customer customer = getCustomerById(CustomerDao.list(), customerId);
            if (customer == null) {
                System.out.println("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
            } else {
                customer.displayInformation();
                customer.tranfers(scanner);
            }
        } else {
            throw new CustomerIdNotValidException("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
        }
    }

    //Hien thi thong tin cac khach hang va danh sach tai khoan cua ho
    public void displayInformation() {
        for (Customer customer : CustomerDao.list()) {
            customer.displayInformation();
        }
    }

    //Hien thi thong tin 1 khach hang, cac tai khoan va giao dich cua tung tai khoan
    public void displayTransaction(String customerId) throws CustomerIdNotValidException {
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            //Lay ra customer co id bang id trong tham so phuong thuc
            Customer customer = getCustomerById(CustomerDao.list(), customerId);
            if (customer == null) {
                System.out.println("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
            } else {
                customer.displayInformation();
                customer.displayTransactionInformation();
            }
        } else {
            throw new CustomerIdNotValidException("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
        }
    }


    //Them tai khoan ATM
    public void addSavingAccount(Scanner scanner, String customerId) throws CustomerIdNotValidException {
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            //Lay ra customer co id bang id trong tham so phuong thuc
            Customer customer = getCustomerById(CustomerDao.list(), customerId);
            if (customer == null) {
                System.out.println("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
            } else {
                SavingAccount account = new SavingAccount();
                account.setCustomerId(customerId);
                account.input(scanner);
                if (isAccountExisted(AccountDao.list(), account)) {
                    System.out.println("Tai khoan da ton tai");
                } else {
                    System.out.println("Tao tai khoan thanh cong");
                    AccountDao.update(account);
                }
            }
        } else {
            throw new CustomerIdNotValidException("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
        }
    }

    //Kiem tra tai khoan co ton tai hay khong
    public boolean isAccountExisted(List<Account> accountList, Account newAccount) {
        return accountList
                .stream()
                .anyMatch(account -> account.getAccountNumber().equals(newAccount.getAccountNumber()));
    }

    //Kiem tra khach hang co ton tai hay khong
    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        return customers
                .stream()
                .anyMatch(customer -> customer.getCustomerId().equals(newCustomer.getCustomerId()));
    }

    //Lay ra customer co id bang id cho truoc
    public Customer getCustomerById(List<Customer> customerList, String customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

}
