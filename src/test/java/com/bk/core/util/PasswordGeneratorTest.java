package com.bk.core.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void testGenerateRandomPassword_LengthAndNotNull() {
        String password = PasswordGenerator.generateRandomPassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }

    @RepeatedTest(5)
    void testGenerateRandomPassword_Uniqueness() {
        String password1 = PasswordGenerator.generateRandomPassword();
        String password2 = PasswordGenerator.generateRandomPassword();
        assertNotEquals(password1, password2);
    }

    @Test
    void testGenerateRandomPassword_ContainsValidCharacters() {
        String password = PasswordGenerator.generateRandomPassword();
        assertTrue(password.matches("[A-Za-z0-9]{10}"));
    }
}
