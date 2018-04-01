package com.zsf.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class SHAUtils {

    private SHAUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     *
     * @param strText 待加密字符串
     * @param algorithm sha加密算法
     * @return
     */
    private String encryptSHA(final String strText, final String algorithm) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并传入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();

                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
