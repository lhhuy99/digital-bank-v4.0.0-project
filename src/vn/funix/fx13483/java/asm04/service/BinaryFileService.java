package vn.funix.fx13483.java.asm04.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {

    //Phuong thuc doc file. Dau vao la duong dan thu muc, dau ra la danh sach doi tuong
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();

        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }

        return objects;
    }

    //Phuong thuc ghi file nhi phan, nhan dau vao la duong dan den thu muc va danh sach doi tuong can luu
    public static <T> void writeFile(String fileName, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {
                file.writeObject(object);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
