package com.zero.customer;

import com.zero.customer.util.DocumentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author yezhaoxing
 * @date 2017/09/16
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentTest {

    @Resource
    private DocumentUtil documentUtil;

    @Test
    public void getLength() {
        File file = new File("E:\\《亿级流量电商详情页系统实战（第二版）：缓存架构+高可用服务架构+微服务架构》.doc");
        System.out.println(file.length());
        System.out.println(123);
    }

}
