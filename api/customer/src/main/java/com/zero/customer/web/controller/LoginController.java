package com.zero.customer.web.controller;

import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.customer.enums.CustomerCodeEnum;
import com.zero.customer.service.LoginService;
import com.zero.customer.service.MessageService;
import com.zero.customer.util.CaptchaUtil;
import com.zero.customer.util.SessionHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @Resource
    private LoginService loginService;
    @Resource
    private MessageService messageService;

    @GetMapping(value = "/captcha")
    @ApiOperation("生成图形验证码")
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaUtil.generate(request, response);
    }

    @GetMapping(value = "/sendMsg")
    @ApiOperation("发送短信")
    public BaseReturnVo sendMsg(HttpServletRequest request,
            @ApiParam(value = "手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "短信类型", required = true) @RequestParam Integer type,
            @ApiParam(value = "图形验证码", required = true) @RequestParam String userInputCaptcha)
            throws IOException, BaseException {
        if (!captchaUtil.validate(request, userInputCaptcha)) {
            throw new BaseException(CustomerCodeEnum.CAPTCHA_WRONG, "验证码错误");
        }
        messageService.sendMsg(phone, type);
        return BaseReturnVo.success();
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
