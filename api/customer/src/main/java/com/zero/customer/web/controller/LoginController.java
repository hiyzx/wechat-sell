package com.zero.customer.web.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.customer.util.CaptchaUtil;
import com.zero.customer.util.SessionHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@RestController
@RequestMapping("/auth")
@Api(description = "用户登录相关接口")
public class LoginController {

    @Resource
    private SessionHelper sessionHelper;
    @Resource
    private CaptchaUtil captchaUtil;

    @GetMapping(value = "/authImage")
    @ApiOperation("生成图形验证码")
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaUtil.generate(request, response);
    }

    @PostMapping(value = "/register.json")
    @ApiOperation("注册")
    public BaseReturnVo register(@RequestParam String username) throws Exception {
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/login.json")
    @ApiOperation("登陆")
    public BaseReturnVo login(@RequestParam String username, @RequestParam String password,
            @RequestParam Boolean rememberMe) throws Exception {
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/logout.json")
    @ApiOperation("注销")
    public BaseReturnVo logout(@RequestParam String sessionId) throws Exception {
        sessionHelper.clearSession(sessionId);
        return BaseReturnVo.success();
    }
}
