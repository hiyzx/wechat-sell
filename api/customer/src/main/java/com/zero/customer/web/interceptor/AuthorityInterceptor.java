package com.zero.customer.web.interceptor;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.util.DateHelper;
import com.zero.customer.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Pointcut(value = "@annotation(com.zero.customer.annotation.Authorize) ")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object preHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> argMap = this.getArgsMap(joinPoint);
        String sessionId = (String) argMap.get("sessionId");
        Date expiration = JwtTokenUtil.validateToken(sessionId);
        if (DateHelper.secondsBetween(new Date(), expiration) < 60 * 10) {
            throw new BaseException(CodeEnum.TOKEN_SOON_EXPIRE, "token expire < 10 minutes");
        }
        return joinPoint.proceed();
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