package com.snipper.Snipper.Snippets.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class CryptoUtil {
    //AES Advanced Encryption Standard
    private static final String ALGORITHM = "AES"; //encryption algorithm
    private static final String ENCRYPTION_KEY = "mySuperSecretKey";

    //Encrypt string
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //Encode this encryption data in base64 format for more readability
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    //Decrypt string
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //Decrypt the encoded data
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}
