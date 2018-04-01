package com.zsf.util.encryption;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @version 1.0
 */
public final class EncryptUtil {

    private EncryptUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String encrypt(String plainText, String key) {
        Key secretKey = getKey(key);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            String encodedStr = new String(Base64.encodeBase64(encoded.getBytes("UTF-8")),"UTF-8");
            return encodedStr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final String decrypt(String cipherText, String key) {
        Key secretKey = getKey(key);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            cipherText = new String(Base64.decodeBase64(cipherText.getBytes("UTF-8")), "utf-8");
            byte[] c = decoder.decodeBuffer(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public static final String encrypt(String plainText) {
        return encrypt(plainText, "zdk");
    }

    /**
     * 根据密钥对指定的密文cipherText进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public static final String decrypt(String cipherText) {
        return decrypt(cipherText, "zdk");
    }

    private static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String name = "xsc";
        String key = MD5Utils.encryptMD5("2342");
        try {
            String encrypt = encrypt(name, key);
            System.out.println("encrypt:" + encrypt);

            String decrypt = decrypt(encrypt, key);
            System.out.println("decrypt:" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
