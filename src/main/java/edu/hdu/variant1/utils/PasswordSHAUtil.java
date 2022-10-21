package edu.hdu.variant1.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Singleton PasswordSHAUtil
 */
public class PasswordSHAUtil {
    private PasswordSHAUtil() {
    }

    /**
     * This is a clever use of the classloading mechanism in JVM to ensure that the instance
     * is initialized with only one thread (thread-safe).
     * The static inner class is not initialized immediately when the Singleton class is
     * loaded, but when getInstance is used (lazy loading).
     */
    private static class SingletonInstance {
        private static final PasswordSHAUtil INSTANCE = new PasswordSHAUtil();
    }

    public static PasswordSHAUtil getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * Implement SHA-256 encryption using Apache's utility classes.
     * @param str : password which we want to transfer
     * @return password which have been SHA-256 encoded
     */
    public String encodeSHA256(String str){
        MessageDigest messageDigest;
        String encodedStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            encodedStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodedStr;
    }
}
