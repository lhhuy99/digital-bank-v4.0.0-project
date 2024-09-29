package vn.funix.fx13483.java.asm04.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerIdValidatorTest {
    CustomerIdValidator customerIdValidator;
    @Test
    public void validateCustomerId() {
        customerIdValidator = new CustomerIdValidator();
        assertTrue(customerIdValidator.validateCustomerId("001215000001"));
        assertFalse(customerIdValidator.validateCustomerId("0012150000ab"));
    }
}