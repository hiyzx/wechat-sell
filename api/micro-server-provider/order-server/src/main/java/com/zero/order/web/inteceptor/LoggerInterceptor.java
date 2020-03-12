package com.zero.order.web.inteceptor;

import com.zero.common.exception.BaseException;
import com.zero.common.util.JsonHelper;
import com.zero.order.util.IpUtil;
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

    @Pointcut("execution(public * com.zero.order.web.controller.*.*(..))")
    private void logController() {
    }

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
        return sb;
    }
}