package com.ensk.exp.mape.util;

import java.math.BigDecimal;

public class FormatUtil {

    public static double setDecimalScale(double amount) {
        BigDecimal b = new BigDecimal(amount);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double setDecimalScale(double amount, int newScale) {
        BigDecimal b = new BigDecimal(amount);
        return b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double setDecimalScale(double amount, int newScale, int roundingMode) {
        BigDecimal b = new BigDecimal(amount);
        return b.setScale(newScale, roundingMode).doubleValue();
    }
}
