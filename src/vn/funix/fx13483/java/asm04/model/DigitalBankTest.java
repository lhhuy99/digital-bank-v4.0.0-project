package vn.funix.fx13483.java.asm04.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DigitalBankTest {
    Customer customer1;
    Customer customer2;
    Customer customer3;
    Customer customer4;
    SavingAccount account1;
    SavingAccount account2;
    SavingAccount account3;
    DigitalBank bank;
    List<Customer> customerList;
    List<Account> accountList;

    @Before
    public void setup() {
        bank = new DigitalBank();
        customer1 = new Customer("001215000001", "Le Van A");
        customer2 = new Customer("001215000002", "Le Van B");
        customer3 = new Customer("001215000003", "Le Van C");
        customer4 = new Customer("001215000004", "Le Van D");
        account1 = new SavingAccount();
        account1.setAccountNumber("123456");
        account2 = new SavingAccount();
        account2.setAccountNumber("234567");
        account3 = new SavingAccount();
        account3.setAccountNumber("345678");

        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
    }

    @Test
    public void isAccountExisted() {
        assertTrue(bank.isAccountExisted(accountList, account1));
        assertFalse(bank.isAccountExisted(accountList, account3));
    }

    @Test
    public void isCustomerExisted() {
        assertTrue(bank.isCustomerExisted(customerList, customer2));
        assertFalse(bank.isCustomerExisted(customerList, customer4));
    }

    @Test
    public void getCustomerById() {
        assertEquals(customer2, bank.getCustomerById(customerList ,"001215000002"));
        assertNotEquals(customer3, bank.getCustomerById(customerList ,"001215000002"));
    }
}