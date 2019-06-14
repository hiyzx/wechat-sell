package com.zero.user.web.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.user.annotation.Authorize;
import com.zero.user.annotation.SecurityTag;
import com.zero.user.facade.UserServerFacade;
import com.zero.user.util.JwtTokenUtil;
import com.zero.user.vo.CheckRecordVo;
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
    private UserServerFacade userService;

    @Authorize
    @PostMapping(value = "/check")
    @SecurityTag
    @ApiOperation("签到")
    public BaseReturnVo check(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization) throws Exception {
        userService.check(JwtTokenUtil.parseUserId(sessionId));
        return BaseReturnVo.success();
    }

    @Authorize
    @GetMapping(value = "/queryCheckRecord")
    @SecurityTag
    @ApiOperation("查看签到记录")
    public ReturnVo<CheckRecordVo> queryCheckRecord(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization) throws Exception {
        return ReturnVo.success(userService.queryCheckRecord(JwtTokenUtil.parseUserId(sessionId)));
    }

}
