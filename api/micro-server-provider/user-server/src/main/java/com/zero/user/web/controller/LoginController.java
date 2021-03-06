package com.zero.user.web.controller;

import com.zero.common.constants.SystemConstants;
import com.zero.common.exception.BaseException;
import com.zero.common.po.User;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.user.annotation.Authorize;
import com.zero.user.annotation.SecurityTag;
import com.zero.user.enums.CustomerCodeEnum;
import com.zero.user.facade.LoginService;
import com.zero.user.facade.MessageService;
import com.zero.user.util.CaptchaUtil;
import com.zero.user.util.JwtTokenUtil;
import com.zero.user.vo.UserLoginResponseVo;
import com.zero.user.vo.UserResponseVo;
import com.zero.user.vo.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @PostMapping(value = "/sendMsg")
    @SecurityTag
    @ApiOperation("发送短信")
    public BaseReturnVo sendMsg(HttpServletRequest request,
        @ApiParam(value = "手机号", required = true) @RequestParam String phone,
        @ApiParam(value = "短信类型-1:注册,2:忘记密码", required = true) @RequestParam Integer type,
        @ApiParam(value = "图形验证码", required = true) @RequestParam String userInputCaptcha)
        throws BaseException, IOException {
        if (!captchaUtil.validate(request, userInputCaptcha)) {
            throw new BaseException(CustomerCodeEnum.CAPTCHA_WRONG, "验证码错误");
        }
        messageService.sendMsg(phone, type);
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/verify")
    @SecurityTag
    @ApiOperation("校验短信验证码")
    public BaseReturnVo verify(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
        @ApiParam(value = "短信类型-1:注册,2:忘记密码", required = true) @RequestParam Integer type,
        @ApiParam(value = "图形验证码", required = true) @RequestParam String code) throws BaseException {
        messageService.validMsg(phone, type, code);
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/restPassword")
    @SecurityTag
    @ApiOperation("重置密码")
    public BaseReturnVo restPassword(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
        @ApiParam(value = "密码1", required = true) @RequestParam String password1,
        @ApiParam(value = "密码2", required = true) @RequestParam String password2) throws Exception {
        loginService.restPassword(phone, password1, password2);
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/register")
    @SecurityTag
    @ApiOperation("注册")
    public ReturnVo<User> register(HttpServletRequest request, HttpServletResponse response,
        @RequestBody @Valid UserDto userDto) {
        User user = loginService.register(userDto);
        return ReturnVo.success(user);
    }

    @PostMapping(value = "/login")
    @SecurityTag
    @ApiOperation("登陆")
    public ReturnVo<UserLoginResponseVo> login(HttpServletRequest request,
        @ApiParam(value = "手机号", required = true) @RequestParam String phone,
        @ApiParam(value = "密码", required = true) @RequestParam String password) throws Exception {
        User user = loginService.login(phone, password);
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(user, userResponseVo);
        UserLoginResponseVo loginResponseVo =
            new UserLoginResponseVo(JwtTokenUtil.generateJwt(user, SystemConstants.JWT_TTL_MILLIS), userResponseVo);
        return ReturnVo.success(loginResponseVo);
    }

    @PostMapping(value = "/refreshToken")
    @SecurityTag
    @ApiOperation("刷新token")
    public ReturnVo<String> refreshToken(@RequestParam String sessionId) throws Exception {
        JwtTokenUtil.validateToken(sessionId);
        return ReturnVo.success(JwtTokenUtil.refreshToken(sessionId));
    }

    @Authorize
    @PostMapping(value = "/heartBeat")
    @ApiOperation("心跳")
    public BaseReturnVo heartBeat(@RequestParam String sessionId) throws Exception {
        return BaseReturnVo.success();
    }
}
