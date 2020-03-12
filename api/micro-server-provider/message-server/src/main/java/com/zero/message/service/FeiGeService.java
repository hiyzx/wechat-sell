package com.zero.message.service;

import com.zero.common.util.HttpClient;
import com.zero.common.util.JsonHelper;
import com.zero.message.dto.req.SendMsgRequest;
import com.zero.message.dto.resp.FeiGeListResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        String jsonStr = feiGeHttpClient.get("/api/userlist", params, null);
        return JsonHelper.readValue(jsonStr, FeiGeListResponseVo.class);
    }

    public void sendMsgAlone(SendMsgRequest sendMsgRequest) {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", secretKey);
        params.put("uid", String.valueOf(sendMsgRequest.getUid()));
        params.put("key", sendMsgRequest.getMsgTemplateKey());
        params.put("title", sendMsgRequest.getTitle());
        params.put("content", sendMsgRequest.getContent());
        params.put("remark", sendMsgRequest.getRemark());
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
