package com.zero.admin.web.controller;

import com.zero.admin.util.RedisHelper;
import com.zero.admin.util.WeChatUtil;
import com.zero.common.constants.SystemConstants;
import com.zero.common.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@RestController
@RequestMapping("/weChat")
@Api(description = "微信相关接口")
@Slf4j
public class WeChatController {

    @Resource
    private RedisHelper<String, String> redisHelper;

    @GetMapping("/checkToken")
    @ApiOperation("微信校验token")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String sortString = WeChatUtil.sort(SystemConstants.TOKEN, timestamp, nonce);// 排序
        String mytoken = WeChatUtil.SHA1(sortString); // 加密
        if (!Objects.equals(mytoken, "") && mytoken.equals(signature)) {// 校验签名
            log.info("签名校验通过。");
            response.getWriter().println(echostr); // 如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        } else {
            log.error("签名校验失败。");
        }
    }

    @GetMapping("/token")
    @ApiOperation("获取access_token")
    public ReturnVo<String> getAccessToken() {
        return ReturnVo.success(redisHelper.get(SystemConstants.REDIS_KEY_WECHAT_ACCESS_TOKEN));
    }
}
