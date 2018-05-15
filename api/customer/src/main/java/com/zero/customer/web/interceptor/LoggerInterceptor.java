package com.zero.customer.web.interceptor;

import com.zero.common.exception.BaseException;
import com.zero.common.util.JsonHelper;
import com.zero.customer.util.IpUtil;
import com.zero.customer.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Aspect
@Component
@Slf4j
public class LoggerInterceptor {
    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(public * com.zero.customer.web.controller.*.*(..))")
    private void logController() {
    }

    /*@Around(value = "logController()")
    public Object timeLog(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            log.error("统计某方法执行耗时环绕通知出错", e);
        }
        long endTime = System.currentTimeMillis();
        // 获取执行的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        // 打印耗时的信息
        log.info("request {} cost time {}", methodName, (endTime - startTime));
        return obj;
    }*/

    @AfterReturning(value = "logController()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        try {
            log.info("request={} || response={}", parseRequest(), JsonHelper.toJSon(returnValue));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @AfterThrowing(value = "logController()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        try {
            if (e instanceof BaseException) {
                BaseException baseException = (BaseException) e;
                log.info("request={} || exceptionCode={}, exceptionMessage={}", parseRequest(),
                        baseException.getCodeEnum().getCodeEnum(), baseException.getMessage());
            } else {
                log.info("request={} || exceptionMessage={}", parseRequest(), e.getMessage());
            }
        } catch (Exception e1) {
            log.error(e.getMessage(), e);
        }
    }

    private StringBuilder parseRequest() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getRequestURI()).append(", ");
        sb.append("IP->").append(IpUtil.getIpAddress(request));
        Map<String, String[]> parameters = request.getParameterMap();
        if (!parameters.isEmpty()) {
            sb.append(", parameters->[");
            final String template = "%s=%s, ";
            for (Map.Entry<String, String[]> entity : parameters.entrySet()) {
                sb.append(String.format(template, entity.getKey(),
                        StringUtils.arrayToCommaDelimitedString(entity.getValue())));
            }
            sb.delete(sb.length() - 2, sb.length()).append("]");
        }
        String sessionId = request.getParameter("sessionId");
        if (StringUtils.hasText(sessionId)) {
            sb.append(", [sessionId=");
            sb.append(sessionId);
            sb.append(", userId=").append(JwtTokenUtil.parseUserId(sessionId));
            sb.append("]");
        }
        return sb;
    }
}