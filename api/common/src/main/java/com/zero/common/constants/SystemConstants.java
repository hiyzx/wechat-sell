package com.zero.common.constants;

/**
 * @author yezhaoxing
 * @date 2017/09/27
 */
public interface SystemConstants {

    int MESSAGE_TYPE_REGISTER = 1;
    int MESSAGE_TYPE_FORGET = 2;

    String COOKIE_NAME = "customer_cookie";

    int DEFAULT_MAX_AGE = -1;

    String TOKEN = "hiyzx";

    String REDIS_KEY_WECHAT_ACCESS_TOKEN = "redis_key_weChat_access_token";

    String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";

    int JWT_TTL_MILLIS = 7200 * 1000;
}
