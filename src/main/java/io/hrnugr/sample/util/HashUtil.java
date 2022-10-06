package io.hrnugr.sample.util;

import io.hrnugr.sample.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class HashUtil {
    private HashUtil() {
    }

    public static String encryptedPassword(String password) throws CustomException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            log.error("Hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

}
