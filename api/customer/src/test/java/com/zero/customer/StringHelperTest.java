package com.zero.customer;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/09/16
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StringHelperTest {

    @Resource
    private StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("yzx362311");
        System.out.println(result);
    }
}
