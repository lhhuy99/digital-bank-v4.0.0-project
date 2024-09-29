package vn.funix.fx13483.java.asm04.dao;

import vn.funix.fx13483.java.asm04.model.Account;
import vn.funix.fx13483.java.asm04.service.BinaryFileService;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final String FILE_PATH = "store\\accounts.dat";

    //Luu danh sach tai khoan vao file. Input la danh sach tai khoan
    public static void save(List<Account> accounts) {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    //Lay ra danh sach tai khoan tu file. Output la danh sach tai khoan
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }


    //Cap nhat so du cho tai khoan
    public static void update(Account editAccount) {

        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));

        List<Account> updatedAccounts;
        if (!hasExist) {
            updatedAccounts = new ArrayList<>(accounts);
            updatedAccounts.add(editAccount);
        } else {
            updatedAccounts = new ArrayList<>();

            for (Account account : accounts) {

                if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
                    updatedAccounts.add(editAccount);
                } else {
                    updatedAccounts.add(account);
                }
            }
        }
        save(updatedAccounts);
    }


//    //Cap nhat so du cho tai khoan
//    public static void update(Account editAccount) {
//
//        List<Account> accounts = list();
//        List<Account> synchronizedList = Collections.synchronizedList(accounts);
//        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
//
//        List<Account> updatedAccounts;
//        List<Account> synchronizedUpdatedList;
//        if (!hasExist) {
//            updatedAccounts = new ArrayList<>(synchronizedList);
//            //Khien updatedAccounts Thread-safe
//            synchronizedUpdatedList = Collections.synchronizedList(updatedAccounts) ;
//            synchronizedUpdatedList.add(editAccount);
//        } else {
//            updatedAccounts = new ArrayList<>();
//            //Khien updatedAccounts Thread-safe
//            synchronizedUpdatedList = Collections.synchronizedList(updatedAccounts) ;
//            ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//            for (Account account: synchronizedList) {
//                //Su dung multi thread va lambda
//                executorService.execute(() -> {
//                    if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
//                        synchronizedUpdatedList.add(editAccount);
//                    } else {
//                        synchronizedUpdatedList.add(account);
//                    }
//                });
//            }
//
//            executorService.shutdown();
//        }
////        save(synchronizedUpdatedList);
//        for (Account account : synchronizedUpdatedList) {
//            System.out.println(account);
//            System.out.println("test");
//        }
//    }
}
