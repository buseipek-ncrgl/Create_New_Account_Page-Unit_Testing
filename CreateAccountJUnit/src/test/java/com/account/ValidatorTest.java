package com.account;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;

    @BeforeEach
    void setUp() {
        firstName = "Alex";
        lastName = "Smith";
        email = "alex@example.com";
        password = "Abc123$#";
        dob = "01/01/2000";
        System.out.println("🧪 Test başladı");
    }

    @AfterEach
    void tearDown() {
        System.out.println("✅ Test bitti\n");
    }

    @Test
    void testValidAccount() {
        assertTrue(Validator.validateAccount(firstName, lastName, email, password, password, dob));
    }

    @Test
    void testInvalidEmail() {
        assertFalse(Validator.isValidEmail("alex.com"));
    }

    @Test
    void testEmptyEmail() {
        assertFalse(Validator.isValidEmail(""));
    }

    @Test
    void testEmptyFirstName() {
        assertFalse(Validator.validateAccount("", lastName, email, password, password, dob));
    }
    
    @Test
    void testSingleCharacterFirstName() {
        assertFalse(Validator.isValidName("A"));
    }

    @Test
    void testPasswordMismatch() {
        assertFalse(Validator.passwordsMatch("Abc123$#", "Xyz789!@"));
    }

    @Test
    void testPasswordMissingLowercase() {
        assertEquals("⚠ Password must contain at least one lowercase letter.", Validator.getPasswordErrorMessage("ABC123$#"));
    }

    @Test
    void testPasswordMissingUppercase() {
        assertEquals("⚠ Password must contain at least one uppercase letter.", Validator.getPasswordErrorMessage("abc123$#"));
    }

    @Test
    void testPasswordMissingDigit() {
        assertEquals("⚠ Password must contain at least one digit.", Validator.getPasswordErrorMessage("Abcdef$#"));
    }

    @Test
    void testPasswordMissingSpecialChar() {
        assertEquals("⚠ Password must contain at least one special character (!@#$%^&* etc.).", Validator.getPasswordErrorMessage("Abc12345"));
    }

    @Test
    void testPasswordEmpty() {
        assertEquals("⚠ Password cannot be empty.", Validator.getPasswordErrorMessage(""));
    }

    @Test
    void testWeakPassword_NoSpecialChar() {
        assertFalse(Validator.isStrongPassword("Abc12345"));
    }
    
    @Test
    void testInvalidDOBFormat() {
        assertFalse(Validator.isValidDOB("2000-12-01"));
    }

    @Test
    void testInvalidDay_TooHigh() {
        assertFalse(Validator.isValidDOB("32/01/2000"));
    }

    @Test
    void testInvalidMonth() {
        assertFalse(Validator.isValidDOB("15/13/2000"));
    }

    @Test
    void testFebruary29_NonLeapYear() {
        assertFalse(Validator.isValidDOB("29/02/2001"));
    }

    @Test
    void testFebruary29_LeapYear() {
        assertTrue(Validator.isValidDOB("29/02/2024"));
    }

}
