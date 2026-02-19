package com.bk.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.SecureRandom;

public class PasswordGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PasswordGenerator.class);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        String password = sb.toString();
        logger.debug("Generated random password: {}", password);
        return password;
    }
}
