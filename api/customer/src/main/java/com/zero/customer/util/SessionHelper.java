package com.zero.customer.util;

import com.zero.common.po.User;
import com.zero.customer.vo.UserLoginResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Configuration
public class SessionHelper {
    private static final Logger LOG = LoggerFactory.getLogger(SessionHelper.class);
    private static final int SESSION_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(30)).intValue();

    @Resource
    private RedisHelper<String, User> redisHelper;

    public int getUserId(String sessionId) {
        User user = this.getUser(sessionId);
        return user == null ? 0 : user.getId();
    }

    public User getUser(String sessionId) {
        User user = null;
        try {
            String key = sessionIdWrapper(sessionId);
            user = redisHelper.get(key);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public void pushUser(UserLoginResponseVo loginResponseVo) throws Exception {
        String sessionId = loginResponseVo.getSessionId();
        LOG.debug("push {}", sessionId);
        String key = sessionIdWrapper(sessionId);
        redisHelper.set(key, loginResponseVo.getUser(), SESSION_EXPIRED_SECONDS);
    }

    public void clearSession(String sessionId) throws Exception {
        LOG.debug("delete {}", sessionId);
        String key = sessionIdWrapper(sessionId);
        redisHelper.delete(key);
    }

    public void heartBeat(String sessionId) throws Exception {
        User user = getUser(sessionId);
        if (user != null) {
            String key = sessionIdWrapper(sessionId);
            LOG.debug("sessionId={} teacherId={} heartbeat", sessionId, user.getId());
            redisHelper.expire(key, SESSION_EXPIRED_SECONDS);
        }
    }

    private String sessionIdWrapper(String sessionId) {
        return String.format("login_%s", sessionId);
    }
}
