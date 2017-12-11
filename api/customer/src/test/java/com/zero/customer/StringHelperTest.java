package com.zero.customer;

import com.zero.common.util.JsonHelper;
import com.zero.customer.util.SensitiveWordFilter;
import com.zero.customer.vo.dto.MessageBody;
import com.zero.customer.vo.dto.NotificationDto;
import eu.medsea.mimeutil.MimeUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collection;

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
        // 生成数据库加密后的密码
        String result = stringEncryptor.encrypt("root");
        System.out.println(result);
    }

    @Test
    public void testJson() {
        String str = "{\"documentId\":\"doc-hkgk3d6mi2mmbt8\",\"title\":\"test\",\"status\":\"PUBLISHED\",\"publishInfo\":{\"pageCount\":1,\"sizeInBytes\":11009,\"publishTime\":\"2017-11-17T06:11:40Z\"},\"notifyTime\":\"2017-11-17T06:11:40Z\"}'); nested exception is com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of com.zero.customer.vo.dto.MessageDto: no String-argument constructor/factory method to deserialize from String value ('{\"documentId\":\"doc-hkgk3d6mi2mmbt8\",\"title\":\"test\",\"status\":\"PUBLISHED\",\"publishInfo\":{\"pageCount\":1,\"sizeInBytes\":11009,\"publishTime\":\"2017-11-17T06:11:40Z\"},\"notifyTime\":\"2017-11-17T06:11:40Z\"}";

        MessageBody messageDto = JsonHelper.readValue(str, MessageBody.class);
        System.out.println(messageDto);

        String str1 = "{\n" + "\"messageId\": \"<message_id>\",\n"
                + "\"messageBody\": \"{\\\"documentId\\\":\\\"doc-gkideq542yb44diy\\\",\\\"title\\\":\\\"doc_title\\\",\\\"status\\\":\\\"PUBLISHED\\\",\\\"publishInfo\\\":{\\\"pageCount\\\":1,\\\"sizeInBytes\\\":33,\\\"publishTime\\\":\\\"2016-12-08T08:45:37Z\\\"},\\\"notifyTime\\\":\\\"2016-12-08T08:45:37Z\\\"}\",\n"
                + " \"notification\": \"<notification_name>\",\n" + " \"server\": \"doc.bj.baidubce.com\"\n" + "}";
        NotificationDto notificationDto = JsonHelper.readValue(str1, NotificationDto.class);
        System.out.println(notificationDto);
    }

    @Test
    public void testFile() {
        File file = new File("E:\\test.txt");
        Collection mimeTypes = MimeUtil.getMimeTypes(file);
        System.out.println(mimeTypes);

        System.out.println(MimeUtil.getMimeTypes(new File("E:\\test.docx")));
    }

    @Test
    public void testSensitiveWordFilter() {
        System.out.println(SensitiveWordFilter.doReplaceCharacterWithStar("垃圾"));
    }
}
