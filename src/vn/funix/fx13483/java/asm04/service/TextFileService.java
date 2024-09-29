package vn.funix.fx13483.java.asm04.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    private static Scanner sc;

    //Phuong thuc doc file . Dau vao la duong dan thu muc, dau ra la danh sach thong tin cua khach hang
    public static List<List<String>> readFile(String fileName) {
        List<List<String>> listList = new ArrayList<>();
        List<String> list;
        sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(fileName)));
            sc.useDelimiter(COMMA_DELIMITER);
            while (sc.hasNextLine()) {
                String customerId = sc.next();
                sc.skip(sc.delimiter());
                String customerName = sc.nextLine();
                list = null;
                list = new ArrayList<>();
                list.add(customerId);
                list.add(customerName);
                listList.add(list);
            }
        } catch (IOException e) {
            System.out.println("Tep khong ton tai");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return listList;
    }
}
