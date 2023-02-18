package fa.training.studentmanagement.component.impl;

import fa.training.studentmanagement.component.PasswordEncrypt;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptMD5 implements PasswordEncrypt {
    
    @Override
    public String encrypt(String plainText) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] encodedHash = digest.digest(
                    plainText.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
