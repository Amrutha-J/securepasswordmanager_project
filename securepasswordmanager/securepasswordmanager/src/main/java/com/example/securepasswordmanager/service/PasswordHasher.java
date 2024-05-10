package com.example.securepasswordmanager.service;

import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.crypto.params.SCryptParameters;

public class PasswordHasher {
    public static String hashPassword(String password) {
        byte[] salt = new byte[16]; // 16-byte salt
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        SCryptParameters params = new SCryptParameters(16384, 8, 1);
        byte[] hashedPassword = SCrypt.generate(password.getBytes(), salt, params);

        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        byte[] salt = Arrays.copyOfRange(Base64.getDecoder().decode(hashedPassword), 0, 16);
        byte[] hashedPasswordBytes = Arrays.copyOfRange(Base64.getDecoder().decode(hashedPassword), 16, hashedPassword.length());

        SCryptParameters params = new SCryptParameters(16384, 8, 1);
        byte[] calculatedHash = SCrypt.generate(password.getBytes(), salt, params);

        return Arrays.equals(calculatedHash, hashedPasswordBytes);
    }
}