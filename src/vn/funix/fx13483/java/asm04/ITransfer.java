package vn.funix.fx13483.java.asm04;

import vn.funix.fx13483.java.asm04.model.Account;

public interface ITransfer {
    void transfer(Account receiveAccount, double amount);
}
