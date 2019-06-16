package com.zero.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zero.common.enums.CodeEnum;
import com.zero.common.util.JsonHelper;
import com.zero.common.util.MD5Helper;
import com.zero.zuul.util.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@Component
@Slf4j
public class SecurityFilter extends ZuulFilter {

    @Autowired
    private RedisHelper<String, String> redisHelper;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        try {

            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            String sessionId = request.getParameter("sessionId");
            String timestampStr = request.getParameter("timestamp");
            Long timestamp = StringUtils.hasText(timestampStr) ? Long.valueOf(timestampStr) : 0L;
            String authorization = request.getParameter("authorization");
            String uri = request.getRequestURI();
            if (StringUtils.hasText(sessionId)) {// 需要session的话才做校验
                Long expireTime = 1000 * 60 * 2L;
                if ((System.currentTimeMillis() - timestamp) < expireTime) {// 判断请求的时间戳和现在对比(2分钟有效)
                    String timeMd5 = MD5Helper.MD5Encode(String.valueOf(timestamp));
                    if (timeMd5.equals(authorization)) {// 判断请求参数是否被修改
                        if (!redisHelper.setNx(String.format("%s-%s", uri, authorization), "1", expireTime)) {// 判断是否重复请求
                            handlerResponse(ctx, CodeEnum.REQUEST_REPEAT.getCodeEnum(), "request repeat");
                        }
                    } else {
                        handlerResponse(ctx, CodeEnum.AUTHORIZATION_FAIL.getCodeEnum(), "authorization fail");
                    }
                } else {
                    handlerResponse(ctx, CodeEnum.REQUEST_TIME_OUT.getCodeEnum(), "request time out");
                }
            }
        } catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }

        return null;
    }

    private void handlerResponse(RequestContext context, String code, String msg) throws IOException, ZuulException {
        ModelMap modelMap = new ModelMap();
        modelMap.put("httpCode", code);
        modelMap.put("msg", msg);
        HttpServletResponse response = context.getResponse();
        PrintWriter writer = response.getWriter();
        writer.print(JsonHelper.toJSon(modelMap));
        writer.flush();
    }
}
