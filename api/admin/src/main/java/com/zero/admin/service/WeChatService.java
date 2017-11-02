package com.zero.admin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zero.admin.util.HttpClient;
import com.zero.admin.util.RedisHelper;
import com.zero.common.constants.SystemConstants;
import com.zero.common.util.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Service
@Slf4j
public class WeChatService {

    private static final TypeReference<Map<String, Object>> TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };
    private static final String CONTENT_PATH = "/cgi-bin";
    @Resource(name = "weChatHttpClient")
    private HttpClient weChatHttpClient;
    @Value("${App.grantType}")
    private String appGrantType;
    @Value("${App.ID}")
    private String appID;
    @Value("${App.Secret}")
    private String appSecret;
    @Resource
    private RedisHelper<String, String> redisHelper;

    public void refreshAccessToken() {
        Map<String, String> params = new HashMap<>(5);
        params.put("grant_type", appGrantType);
        params.put("appid", appID);
        params.put("secret", appSecret);
        String resp = weChatHttpClient.get(String.format("%s/%s", CONTENT_PATH, "token"), params);
        log.info("resp= " + resp);
        Map<String, Object> accessTokenMap = JsonHelper.readValue(resp, TYPE_REFERENCE);
        if (accessTokenMap.containsKey("access_token")) {
            String accessToken = accessTokenMap.get("access_token").toString();
            if (accessTokenMap.containsKey("expires_in")) {
                int expiresIn = (Integer) (accessTokenMap.get("expires_in"));
                redisHelper.set(SystemConstants.REDIS_KEY_WECHAT_ACCESS_TOKEN, accessToken, expiresIn);
            }
        } else {
            log.error("getAccessToken error：errCode={},errMsg={}", accessTokenMap.get("errcode"),
                    accessTokenMap.get("errmsg"));
        }
    }
}
