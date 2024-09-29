package vn.funix.fx13483.java.asm04.model;

import vn.funix.fx13483.java.asm04.file.TransactionType;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;
    private final Boolean status;
    private final TransactionType type;
    private static final long serialVersionUID = 1L;

    public Transaction(String accountNumber, double amount, String time, Boolean status, TransactionType type) {
        this.id = String.valueOf(UUID.randomUUID());
        this.time = time;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public Boolean getStatus() {
        return status;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
