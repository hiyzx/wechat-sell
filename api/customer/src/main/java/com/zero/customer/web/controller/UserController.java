package com.zero.customer.web.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.annotation.Authorize;
import com.zero.customer.service.UserService;
import com.zero.customer.util.SessionHelper;
import com.zero.customer.vo.CheckRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/09/25
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户相关接口")
public class UserController {

    @Resource
    private SessionHelper sessionHelper;
    @Resource
    private UserService userService;

    @Authorize
    @PostMapping(value = "/check.json")
    @ApiOperation("签到")
    public BaseReturnVo check(@RequestParam String sessionId) throws Exception {
        userService.check(sessionHelper.getUserId(sessionId));
        return BaseReturnVo.success();
    }

    @Authorize
    @GetMapping(value = "/queryCheckRecord.json")
    @ApiOperation("查看签到记录")
    public ReturnVo<CheckRecordVo> queryCheckRecord(@RequestParam String sessionId) throws Exception {
        return ReturnVo.success(userService.queryCheckRecord(sessionHelper.getUserId(sessionId)));
    }

}
