package com.zero.customer.web.controller;

import com.zero.common.exception.BaseException;
import com.zero.common.po.User;
import com.zero.common.util.StringHelper;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.annotation.Authorize;
import com.zero.customer.enums.CustomerCodeEnum;
import com.zero.customer.service.LoginService;
import com.zero.customer.service.MessageService;
import com.zero.customer.util.CaptchaUtil;
import com.zero.customer.util.SessionHelper;
import com.zero.customer.util.WebHelper;
import com.zero.customer.vo.UserLoginResponseVo;
import com.zero.customer.vo.UserResponseVo;
import com.zero.customer.vo.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import static com.zero.common.constants.SystemConstants.COOKIE_NAME;
import static com.zero.common.constants.SystemConstants.DEFAULT_MAX_AGE;

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

    @PostMapping(value = "/sendMsg.json")
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
    public ReturnVo<UserLoginResponseVo> register(HttpServletRequest request, HttpServletResponse response,
            @RequestBody @Valid UserDto userDto) throws Exception {
        User user = loginService.register(userDto);
        // session放入redis中
        String cookieValue = WebHelper.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isEmpty(cookieValue)) {
            cookieValue = StringHelper.generateUUId();
            WebHelper.setCookie(response, COOKIE_NAME, cookieValue, DEFAULT_MAX_AGE);
        }
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(user, userResponseVo);
        UserLoginResponseVo loginResponseVo = new UserLoginResponseVo(cookieValue, userResponseVo);
        sessionHelper.pushUser(loginResponseVo);
        return ReturnVo.success(loginResponseVo);
    }

    @PostMapping(value = "/login.json")
    @ApiOperation("登陆")
    public ReturnVo<UserLoginResponseVo> login(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String phone, @RequestParam String password) throws Exception {
        User user = loginService.login(phone, password);
        // session放入redis中
        String cookieValue = WebHelper.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isEmpty(cookieValue)) {
            cookieValue = StringHelper.generateUUId();
        }
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(user, userResponseVo);
        UserLoginResponseVo loginResponseVo = new UserLoginResponseVo(cookieValue, userResponseVo);
        sessionHelper.pushUser(loginResponseVo);
        return ReturnVo.success(loginResponseVo);
    }

    @Authorize
    @PostMapping(value = "/logout.json")
    @ApiOperation("注销")
    public BaseReturnVo logout(@RequestParam String sessionId) throws Exception {
        sessionHelper.clearSession(sessionId);
        return BaseReturnVo.success();
    }
}
