package com.zero.customer;

import com.zero.customer.service.FeiGeiService;
import com.zero.customer.util.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HttpClientTest {

    @Resource
    private FeiGeiService feiGeUtil;
    @Resource(name = "remoteHttpClient")
    private HttpClient remoteHttpClient;

    @Test
    public void testList() {
        feiGeUtil.list();
    }

    @Test
    public void testSend() throws IOException {
        String s = remoteHttpClient.get("/customer/monitor/version");
        System.out.println(s);
    }
}
