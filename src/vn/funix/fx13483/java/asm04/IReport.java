package vn.funix.fx13483.java.asm04;

import vn.funix.fx13483.java.asm04.file.TransactionType;
import vn.funix.fx13483.java.asm04.model.Account;

public interface IReport {
    void log(double amount, TransactionType type, Account receiveAccount);
}
