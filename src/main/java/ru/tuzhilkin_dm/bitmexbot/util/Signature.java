package ru.tuzhilkin_dm.bitmexbot.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Signature {
    public String getSignature(String secretKey, String message) throws NoSuchAlgorithmException,
            InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        byte[] hashedBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexStringBuilder = new StringBuilder();

        for (byte b : hashedBytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }

        return hexStringBuilder.toString();
    }
}
