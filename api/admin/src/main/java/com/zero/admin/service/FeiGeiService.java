package com.zero.admin.service;

import com.zero.admin.util.HttpClient;
import com.zero.admin.vo.http.response.FeiGeListResponseVo;
import com.zero.common.util.JsonHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Configuration
public class FeiGeiService {

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

    public void sendMsgAlone(Integer uid, String msgTemplateKey, String title, String content, String remark)
            throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("secret", secretKey);
        params.put("uid", String.valueOf(uid));
        params.put("key", msgTemplateKey);
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
