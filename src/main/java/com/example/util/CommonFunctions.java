package com.example.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonFunctions {

    private static CommonFunctions instance;

    private CommonFunctions() {
        // Private constructor to prevent instantiation from outside
    }

    public static CommonFunctions getInstance() {
        if (instance == null) {
            synchronized (CommonFunctions.class) {
                if (instance == null) {
                    instance = new CommonFunctions();
                }
            }
        }
        return instance;
    }

    private static final String CHARACTERSFORPASSWORD = "abcdefghijklmnopqrstuvwxyz123456789";
    private final SecureRandom random = new SecureRandom();

    public String generateRandomPassword(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERSFORPASSWORD.length());
            char randomChar = CHARACTERSFORPASSWORD.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public String getCurrentDateAndTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a formatter for the date and time format you want
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        return currentDateTime.format(formatter);
    }
}
