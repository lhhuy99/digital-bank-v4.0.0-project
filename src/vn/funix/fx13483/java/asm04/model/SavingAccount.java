package vn.funix.fx13483.java.asm04.model;

import vn.funix.fx13483.java.asm04.IReport;
import vn.funix.fx13483.java.asm04.common.common;
import vn.funix.fx13483.java.asm04.file.TransactionType;

public class SavingAccount extends Account implements IReport {

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    @Override
    public void log(double amount, TransactionType type, Account receiveAccount) {
        if (type.equals(TransactionType.WITHDRAW)) {
            System.out.println("+-------+--------------+-------+");
            System.out.println("BIEN LAI GIAO DICH SAVINGS");
            System.out.printf("NGAY G/D: %32s%n", common.getDateTime());
            System.out.printf("ATM ID: %34s%n", "DIGITAL-BANK-ATM 2024");
            System.out.printf("SO TK: %35s%n", getAccountNumber());
            System.out.printf("SO TIEN RUT: %29s%n", common.formatBalance(amount));
            System.out.printf("SO DU TK: %32s%n", common.formatBalance(getBalance()));
            System.out.printf("PHI + VAT: %31s%n", common.formatBalance(0));
            System.out.println("+-------+--------------+-------+");
        } else if (type.equals(TransactionType.TRANSFER)) {
            System.out.println("+-------+--------------+-------+");
            System.out.println("BIEN LAI GIAO DICH SAVINGS");
            System.out.printf("NGAY G/D: %35s%n", common.getDateTime());
            System.out.printf("ATM ID: %37s%n", "DIGITAL-BANK-ATM 2024");
            System.out.printf("SO TK: %38s%n", getAccountNumber());
            System.out.printf("SO TK NHAN: %33s%n", receiveAccount.getAccountNumber());
            System.out.printf("SO TIEN CHUYEN: %29s%n", common.formatBalance(amount));
            System.out.printf("SO DU TK: %35s%n", common.formatBalance(getBalance()));
            System.out.printf("PHI + VAT: %34s%n", common.formatBalance(0));
            System.out.println("+-------+--------------+-------+");
        }

    }

    public void withdraw(double amount) {
        if (isAccepted(amount)) {
            createTransaction(amount, common.getDateTime(), true, TransactionType.WITHDRAW);
            System.out.println("Rut tien thanh cong, bien lai giao dich:");
            log(amount, TransactionType.WITHDRAW, null);
        } else {
            System.out.println("Rut tien that bai");
        }
    }

    public void tranfers(Account receiveAccount, double amount) {
        if (isAccepted(amount)) {
            createTransaction((amount * -1), common.getDateTime(), true, TransactionType.TRANSFER);
            receiveAccount.createTransaction(amount, common.getDateTime(), true, TransactionType.TRANSFER);
            System.out.println("Chuyen tien thanh cong, bien lai giao dich:");
            log(amount, TransactionType.TRANSFER, receiveAccount);
        } else {
            System.out.println("Rut tien that bai");
        }
    }

    public boolean isAccepted(double amount) {
        if (amount >= 50000 && (amount % 10000 == 0) && (getBalance() - amount >= 50000)) {
            return isPremiumAccount() || !(amount > SAVINGS_ACCOUNT_MAX_WITHDRAW);
        }
        return false;
    }
}
