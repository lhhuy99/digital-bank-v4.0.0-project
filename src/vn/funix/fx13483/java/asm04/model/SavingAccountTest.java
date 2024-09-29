package vn.funix.fx13483.java.asm04.model;

import org.junit.Before;

import static org.junit.Assert.*;

public class SavingAccountTest {
    SavingAccount account;
    SavingAccount receiveAccount;

    @Before
    public void setup() {
        account = new SavingAccount();
        receiveAccount = new SavingAccount();
    }

    @org.junit.Test
    public void withdraw() {
        account.setBalance(10000000);
        account.withdraw(6000000);
        assertEquals(4000000, account.getBalance(), 0);
        account.withdraw(50000);
        assertEquals(3950000, account.getBalance(), 0);
    }

    @org.junit.Test
    public void tranfers() {
        account.setBalance(20000000);
        receiveAccount.setBalance(1000000);

        account.tranfers(receiveAccount, 10000000);
        assertEquals(10000000, account.getBalance(), 0);
        assertEquals(11000000, receiveAccount.getBalance(), 0);
    }

    @org.junit.Test
    public void isAccepted() {
        account.setBalance(10000000);
        assertTrue(account.isAccepted(50000));
        assertTrue(account.isAccepted(6000000));
        assertFalse(account.isAccepted(40000));
        assertFalse(account.isAccepted(555000));
        assertFalse(account.isAccepted(9960000));

        account.setBalance(9000000);
        assertFalse(account.isAccepted(6000000));
    }

    @org.junit.Test
    public void isPremiumAccount() {
        account.setBalance(10000000);
        assertTrue(account.isPremiumAccount());

        account.setBalance(9000000);
        assertFalse(account.isPremiumAccount());
    }
}