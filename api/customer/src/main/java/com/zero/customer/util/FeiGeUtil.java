package com.zero.customer.util;

import com.zero.common.util.JsonHelper;
import com.zero.customer.vo.http.response.FeiGeListResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Configuration
public class FeiGeUtil {

    @Value("${feige.secret.key}")
    private String secretKey;
    @Resource(name = "feiGeHttpClient")
    private HttpClient feiGeHttpClient;

    public FeiGeListResponseVo list() {
        Map<String, String> params = new HashMap<>();
        params.put("secret", secretKey);
        String jsonStr = feiGeHttpClient.get("/api/userlist", params);
        return JsonHelper.readValue(jsonStr, FeiGeListResponseVo.class);
    }

    public void sendMsgAlone(Integer uid, String msgTemplateKey) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("secret", secretKey);
        params.put("uid", String.valueOf(uid));
        params.put("key", msgTemplateKey);
        params.put("title", "加油!");
        params.put("content", "hello world!");
        params.put("remark", "Java is the best language in the world!");
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
