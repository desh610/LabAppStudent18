package com.example.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.example.util.CommonFunctions;

public class CommonFunctionsTest {

    @Test
    public void testGenerateRandomPassword() {
        CommonFunctions commonFunctions = CommonFunctions.getInstance();
        String password = commonFunctions.generateRandomPassword(8);
        // Password length should be 8 characters
        assertEquals(8, password.length());
        // Password should contain only characters from CHARACTERSFORPASSWORD
        assertTrue(password.matches("[a-z0-9]+"));
    }

    @Test
    public void testGetCurrentDateAndTime() {
        CommonFunctions commonFunctions = CommonFunctions.getInstance();
        String expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        String currentDateTime = commonFunctions.getCurrentDateAndTime();
        // Current date and time should be in the expected format
        assertEquals(expectedFormat, currentDateTime);
    }
}
