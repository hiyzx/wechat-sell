package com.zero.common.util;

import java.util.*;

/**
 * 字符串处理类
 * 
 * @author yezhaoxing
 * @date 2017/7/20
 */
public class StringHelper {

    private static UUID uuid = UUID.randomUUID();

    public static String generateMasterKey() {
        return String.format("%s%s", "master", generateKey());
    }

    public static String generateDetailKey() {
        return String.format("%s%s", "detail", generateKey());
    }

    public static String generateProductKey() {
        return String.format("%s%s", "product", generateKey());
    }

    public static List<String> convertStringToList(String source) {
        String[] sourceArr = source.split(",");
        List<String> rtn = new ArrayList<>(sourceArr.length);
        Collections.addAll(rtn, sourceArr);
        return rtn;
    }

    public static String convertListToStr(List<String> source) {
        StringBuilder sb = new StringBuilder();
        int size = source.size();
        for (int i = 0; i < size; i++) {
            sb.append(source.get(i));
            if (i != size - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static String generateKey() {
        return uuid.toString().replaceAll("-", "").substring(0, 20);
    }

    public static String generateCode() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

    public static String generateUUId() {
        return UUID.randomUUID().toString();
    }
}
