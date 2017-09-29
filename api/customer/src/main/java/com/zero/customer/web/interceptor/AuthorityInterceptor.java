package com.zero.customer.web.interceptor;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.customer.util.SessionHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/4/29
 */
@Aspect
@Component
@Slf4j
public class AuthorityInterceptor {

    @Resource
    private SessionHelper sessionHelper;

    @Pointcut(value = "@annotation(com.zero.customer.annotation.Authorize) ")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object preHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> argMap = this.getArgsMap(joinPoint);
        String sessionId = (String) argMap.get("sessionId");
        if (StringUtils.hasText(sessionId) && sessionHelper.getUserId(sessionId) != null) {
            sessionHelper.heartBeat(sessionId);
            return joinPoint.proceed();
        } else {
            throw new BaseException(CodeEnum.NOT_LOGIN, "未登录");
        }
    }

    private Map<String, Object> getArgsMap(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Map<String, Object> args = new LinkedHashMap<>();
        String names[] = signature.getParameterNames();
        for (int i = 0, len = names.length; i < len; i++) {
            args.put(names[i], pjp.getArgs()[i]);
        }
        return args;
    }
}