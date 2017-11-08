package com.zero.admin.web.controller;

import com.zero.admin.service.LoginService;
import com.zero.admin.util.SessionHelper;
import com.zero.admin.util.WebHelper;
import com.zero.admin.vo.UserLoginResponseVo;
import com.zero.common.po.Store;
import com.zero.common.util.StringHelper;
import com.zero.common.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.zero.common.constants.SystemConstants.COOKIE_NAME;

/**
 * @author yezhaoxing
 * @date 2017/11/08
 */
@RestController
@RequestMapping("/auth")
@Api(description = "用户登录相关接口")
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private SessionHelper sessionHelper;

    @PostMapping(value = "/login.json")
    @ApiOperation("登陆")
    public ReturnVo<String> login(HttpServletRequest request,
            @ApiParam(value = "店家名", required = true) @RequestParam String name) throws Exception {
        Store store = loginService.login(name);
        String cookieValue = WebHelper.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isEmpty(cookieValue)) {
            cookieValue = StringHelper.generateUUId();
        }
        UserLoginResponseVo loginResponseVo = new UserLoginResponseVo(cookieValue, store.getId());
        sessionHelper.pushUser(loginResponseVo);
        return ReturnVo.success(cookieValue);
    }
}
