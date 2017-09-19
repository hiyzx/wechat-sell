package com.zero.common.util;

import java.math.BigDecimal;

/**
 * @author yezhaoxing
 * @date 2017/08/17
 */
public class NumberUtil {

    public static double add(double v1, double v2) {// 加法
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {// 减法
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static boolean judge(double v1, double v2) {// 比较两个数的大小 v1>=v2返回true
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue() >= 0;
    }

    public static double mul(double v1, double v2) {// 乘法
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {// 除法
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(double v) {// 截取3位
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Integer roundUp(double v) {// 四舍五入
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 0, BigDecimal.ROUND_UP).intValue();
    }

    public static Long moveByte(long oldHistory, long moveAmonut) {
        long moveResult = oldHistory << moveAmonut;
        return Long.parseLong(toFullBinaryString(moveResult), 2) + 1;
    }

    public static String toFullBinaryString(long num) {
        final int size = 42;
        char[] chs = new char[size];
        for (int i = 0; i < size; i++) {
            chs[size - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }
}
