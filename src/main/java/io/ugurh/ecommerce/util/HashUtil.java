package io.ugurh.ecommerce.util;

import io.ugurh.ecommerce.handler.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author harun ugur
 */
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
