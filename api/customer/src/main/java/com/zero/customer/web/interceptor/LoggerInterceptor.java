package com.zero.customer.web.interceptor;

import com.zero.common.exception.BaseException;
import com.zero.common.util.JsonHelper;
import com.zero.customer.util.IpUtil;
import com.zero.customer.util.SessionHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class LoggerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);
    @Resource
    private HttpServletRequest request;
    @Resource
    private SessionHelper sessionHelper;

    // http://stackoverflow.com/questions/29653664/how-to-correctly-use-spring-aop-to-select-the-execution-of-a-method-annotated-wi
    @Pointcut("execution(public * com.zero.customer.web.controller.*.*(..))")
    // @Pointcut("within(@org.springframework.stereotype.Controller *) &&
    // @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void logController() {
    };

    @AfterReturning(value = "logController()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        try {
            LOG.info("request={} || response={}", parseRequest(), JsonHelper.toJSon(returnValue));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @AfterThrowing(value = "logController()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        try {
            if (e instanceof BaseException) {
                BaseException baseException = (BaseException) e;
                LOG.info("request={} || exceptionCode={}, exceptionMessage={}", parseRequest(),
                        baseException.getCodeEnum().getCodeEnum(), baseException.getMessage());
            } else {
                LOG.info("request={} || exceptionMessage={}", parseRequest(), e.getMessage());
            }
        } catch (Exception e1) {
            LOG.error(e.getMessage(), e);
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
            sb.append(", userId=").append(sessionHelper.getUserId(sessionId));
            sb.append("]");
        }
        return sb;
    }
}