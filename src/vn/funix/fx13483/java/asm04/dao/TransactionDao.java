package vn.funix.fx13483.java.asm04.dao;

import vn.funix.fx13483.java.asm04.model.Transaction;
import vn.funix.fx13483.java.asm04.service.BinaryFileService;

import java.util.List;

public class TransactionDao {
    private static final String FILE_PATH = "store\\transactions.dat";

    //Luu danh sach giao dich vao file. Input la danh sach giao dich
    public static void save(List<Transaction> transactions) {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    //Lay ra danh sach giao dich tu file. Output la danh sach giao dich
    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    //Them moi transaction vao file
    public static void update(Transaction transaction) {
        List<Transaction> transactions = list();
        transactions.add(transaction);

        save(transactions);
    }
}
