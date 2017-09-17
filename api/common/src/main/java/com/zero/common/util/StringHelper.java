package com.zero.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串处理类
 * 
 * @author yezhaoxing
 * @date 2017/7/20
 */
public class StringHelper {

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
}
