package com.zero.customer.web.interceptor;

import com.zero.common.constants.SystemConstants;
import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.util.DateHelper;
import com.zero.customer.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before(value = "pointCut()")
    public void auth(JoinPoint joinPoint) throws BaseException {
        Map<String, Object> argMap = this.getArgsMap(joinPoint);
        String sessionId = (String) argMap.get("sessionId");
        Date expiration = JwtTokenUtil.validateToken(sessionId);
        if (DateHelper.secondsBetween(new Date(), expiration) < SystemConstants.TOKEN_EXPIRE_AFTER) {
            throw new BaseException(CodeEnum.TOKEN_SOON_EXPIRE, "token expire < 10 minutes");
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