package com.sijibomiaol.skaetAss.utills;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.time.Year;
import java.util.Base64;

import static com.sijibomiaol.skaetAss.constant.AppConstant.SecurityConstants.*;

@Slf4j
public class SecurityUtils {
    public static String encryptPin(String pin, String secretKey) {
        String encryptedPin = "";

        try {
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), SALT, ITERATION_COUNT);
            AlgorithmParameterSpec algorithmParameterSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameterSpec);

            byte[] encrpyt = cipher.doFinal(pin.getBytes(StandardCharsets.UTF_8));

            encryptedPin = new String(Base64.getEncoder().encodeToString(encrpyt));

        } catch (Exception e) {
            log.error("error occured :: {}", e.getMessage());
        }
        return encryptedPin;
    }

    private static String decryptPin(String encryptedPin, String secretKey) {
        try {
            byte[] decrypt = Base64.getDecoder().decode(encryptedPin);
            KeySpec keySpec = new PBEKeySpec (secretKey.toCharArray(), SALT, ITERATION_COUNT);
            AlgorithmParameterSpec algorithmParameterSpec = new PBEParameterSpec(decrypt, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            Cipher decipher = Cipher.getInstance(key.getAlgorithm());
            decipher.init(Cipher.DECRYPT_MODE, key, algorithmParameterSpec);
            byte[] decoded = decipher.doFinal(decrypt);

            encryptedPin = new String(decoded, StandardCharsets.UTF_8);

        } catch (Exception e) {
            log.error("error occured :: {}", e.getMessage());
        }
        return encryptedPin;
    }

    public static String encryptStringData(String data) {
        return encryptPin(data, DEFUALT_KEY);
    }

    public static String decryptStringData(String data) {
        return decryptPin(data, DEFUALT_KEY);
    }

    public static String twoXEncryptStringData(String data) {
        return encryptStringData(encryptStringData(data));
    }

    public static String twoXDecryptStringData(String data) {
        return decryptStringData(decryptStringData(data));
    }


}
