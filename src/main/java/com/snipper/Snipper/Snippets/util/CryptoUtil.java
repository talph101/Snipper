package com.snipper.Snipper.Snippets.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
//import java.util.Based64;


public class CryptoUtil {
    private static final String ALGORITHM = "AES"; //encryption algorithm
    private static final String ENCRYPTION_KEY = "mySuperSecretKey";

    //Encrypt string
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }
}
