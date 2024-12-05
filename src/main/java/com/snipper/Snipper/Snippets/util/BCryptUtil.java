package com.snipper.Snipper.Snippets.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {
    //Method to hash password

    public static String generatedSecurePassword(String originalPassword){
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt());
    }

    public static boolean mathPasswords(String originalPassword, String generatedSecurePassword){
        return BCrypt.checkpw(originalPassword, generatedSecurePassword);
    }
}
