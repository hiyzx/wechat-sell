package com.zero.zuul;

import org.junit.Test;

import com.zero.common.util.MD5Helper;

/**
 * @author yezhaoxing
 * @date 2019/7/9
 */
public class MD5Test {

    @Test
    public void test() {
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis);
        System.out.println(MD5Helper.MD5Encode(String.valueOf(timeMillis)));
    }
}
