package com.zero.admin.util;

import com.zero.admin.vo.UserLoginResponseVo;
import com.zero.common.util.RedisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Configuration
public class SessionHelper {
    private static final Logger LOG = LoggerFactory.getLogger(SessionHelper.class);
    private static final int SESSION_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(30)).intValue();

    @Resource
    private RedisHelper<String, Integer> redisHelper;

    public Integer getUserId(String sessionId) {
        String key = sessionIdWrapper(sessionId);
        return redisHelper.get(key);
    }

    public void pushUser(UserLoginResponseVo loginResponseVo) throws Exception {
        String sessionId = loginResponseVo.getCookieValue();
        LOG.debug("push {}", sessionId);
        String key = sessionIdWrapper(sessionId);
        redisHelper.set(key, loginResponseVo.getStoreId(), SESSION_EXPIRED_SECONDS);
    }

    public void clearSession(String sessionId) throws Exception {
        LOG.debug("delete {}", sessionId);
        String key = sessionIdWrapper(sessionId);
        redisHelper.delete(key);
    }

    public void heartBeat(String sessionId) throws Exception {
        Integer userId = getUserId(sessionId);
        if (userId != null) {
            String key = sessionIdWrapper(sessionId);
            LOG.debug("sessionId={} teacherId={} heartbeat", sessionId, userId);
            redisHelper.expire(key, SESSION_EXPIRED_SECONDS);
        }
    }

    private String sessionIdWrapper(String sessionId) {
        return String.format("admin_login_%s", sessionId);
    }
}
