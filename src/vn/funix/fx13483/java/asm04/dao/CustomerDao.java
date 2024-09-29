package vn.funix.fx13483.java.asm04.dao;

import vn.funix.fx13483.java.asm04.model.Customer;
import vn.funix.fx13483.java.asm04.service.BinaryFileService;

import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "store\\customers.dat";

    //Luu danh sach khach hang vao file, input la danh sach khach hang
    public static void save(List<Customer> customers) {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    //Lay ra danh sach khach hang tu file, output la danh sach khach hang
    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    //Them khach hang vao danh sach
    public static void update(Customer customer) {
        List<Customer> customers = list();
        customers.add(customer);

        save(customers);
    }

}
