package com.zsf.util;

import java.math.BigDecimal;

/**
 * 金额操作
 */
public final class MoneyUtils {

    private MoneyUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private final static BigDecimal SCALE_100 = new BigDecimal("100");

    /**
     * 分转换成元
     * @param fen
     * @return
     */
    public static BigDecimal fenToYuan(BigDecimal fen) {
        if (fen == null) {
            return BigDecimal.ZERO;
        }
        return fen.divide(SCALE_100).setScale(2);
    }

    /**
     * 元转换成分
     * @param yuan
     * @return
     */
    public static BigDecimal yuanToFen(BigDecimal yuan) {
        if (yuan == null) {
            return BigDecimal.ZERO;
        }
        return yuan.multiply(SCALE_100).setScale(2);
    }




}
