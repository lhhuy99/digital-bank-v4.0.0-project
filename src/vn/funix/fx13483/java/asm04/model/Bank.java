package vn.funix.fx13483.java.asm04.model;

import java.util.Scanner;
import java.util.UUID;

public class Bank {
    private String bankId;
    private String bankName;
    private static final Scanner sc = new Scanner(System.in);

    public Bank() {
        this.bankId = String.valueOf(UUID.randomUUID());
        this.bankName = "Digital Bank";
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

}
