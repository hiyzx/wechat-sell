package com.zero.customer.web.interceptor;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.util.MD5Helper;
import com.zero.common.util.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @description 安全校验拦截器
 * @date 2018/03/14
 */
//@Aspect
@Component
@Slf4j
public class SecurityInterceptor {

    @Resource
    private RedisHelper<String, String> redisHelper;
    @Resource
    private HttpServletRequest request;
    @Value("spring.profile.active")
    private String profileActive;

    @Pointcut(value = "@annotation(com.zero.customer.annotation.SecurityTag) ")
    private void webController() {
    }

    @Before(value = "webController()")
    public void preHandle(JoinPoint joinPoint) throws Throwable {
        if ("dev".equals(profileActive)) {
            Map<String, Object> argMap = this.getArgsMap(joinPoint);
            String uri = request.getRequestURI();
            Long expireTime = 1000 * 60 * 2L;
            Long timestamp = (Long) argMap.get("timestamp");
            String authorization = (String) argMap.get("authorization");
            if ((System.currentTimeMillis() - timestamp) < expireTime) {// 判断请求的时间戳和现在对比(2分钟有效)
                String timeMd5 = MD5Helper.MD5Encode(String.valueOf(timestamp));
                if (timeMd5.equals(authorization)) {// 判断请求参数是否被修改
                    if (!redisHelper.setNx(String.format("%s-%s", uri, authorization), "1", expireTime)) {// 判断是否重复请求
                        throw new BaseException(CodeEnum.REQUEST_REPEAT, "request repeat");
                    }
                } else {
                    throw new BaseException(CodeEnum.AUTHORIZATION_FAIL, "authorization fail");
                }
            } else {
                throw new BaseException(CodeEnum.REQUEST_TIME_OUT, "request time out");
            }
        }
    }

    private Map<String, Object> getArgsMap(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Map<String, Object> args = new LinkedHashMap<>();
        String[] names = signature.getParameterNames();
        for (int i = 0, len = names.length; i < len; i++) {
            args.put(names[i], pjp.getArgs()[i]);
        }
        return args;
    }
}
