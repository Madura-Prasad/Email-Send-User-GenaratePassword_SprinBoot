package com.example.demo.Model;

import java.security.SecureRandom;

public class passwordGenerate {

    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final String ALL_CHARACTERS = CAPITAL_LETTERS + SMALL_LETTERS + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom random = new SecureRandom();

    public static String generateStrongPassword(int length) {
        StringBuilder password = new StringBuilder();
        boolean hasCapital = false;
        boolean hasSmall = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < length; i++) {
            char randomChar = ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length()));
            password.append(randomChar);

            if (CAPITAL_LETTERS.contains(String.valueOf(randomChar))) {
                hasCapital = true;
            } else if (SMALL_LETTERS.contains(String.valueOf(randomChar))) {
                hasSmall = true;
            } else if (DIGITS.contains(String.valueOf(randomChar))) {
                hasDigit = true;
            } else if (SPECIAL_CHARACTERS.contains(String.valueOf(randomChar))) {
                hasSpecial = true;
            }
        }

        // Ensure the password has at least one of each type of character
        if (!hasCapital || !hasSmall || !hasDigit || !hasSpecial) {
            // Recursively generate a new password if any type is missing
            return generateStrongPassword(length);
        }

        return password.toString();
    }
}