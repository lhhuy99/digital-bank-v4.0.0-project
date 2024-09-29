package vn.funix.fx13483.java.asm04.common;

public class CustomerIdValidator {

    //Xac minh customerId co hop le hay khong
    public static boolean validateCustomerId(String customerId) {
        try {
            long checkException = Long.parseLong(customerId);
            return customerId.length() == 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
