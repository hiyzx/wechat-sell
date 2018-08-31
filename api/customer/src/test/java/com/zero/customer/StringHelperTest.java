package com.zero.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import com.zero.common.util.MD5Helper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yezhaoxing
 * @since 2018/08/31
 */
@RunWith(JUnit4.class)
@SpringBootTest
@Slf4j
public class StringHelperTest {

    @Test
    public void testGenerateSingurate() {
        long timeMillis = System.currentTimeMillis();
        log.info("{}", timeMillis);
        log.info("{}", MD5Helper.MD5Encode(String.valueOf(timeMillis)));
    }
}
