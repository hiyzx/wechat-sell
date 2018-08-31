package com.zero.customer.service;

import com.zero.common.util.JsonHelper;
import com.zero.customer.util.HttpClient;
import com.zero.customer.vo.http.response.FeiGeListResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Service
public class FeiGeService {

    @Value("${feige.secret.key}")
    private String secretKey;
    @Resource(name = "feiGeHttpClient")
    private HttpClient feiGeHttpClient;

    public FeiGeListResponseVo list() {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", secretKey);
        String jsonStr = feiGeHttpClient.get("/api/userlist", params);
        return JsonHelper.readValue(jsonStr, FeiGeListResponseVo.class);
    }

    public void sendMsgAlone(Integer uid, String msgTemplateKey, String title, String content, String remark)
            throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", secretKey);
        params.put("uid", String.valueOf(uid));
        params.put("key", msgTemplateKey);
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
