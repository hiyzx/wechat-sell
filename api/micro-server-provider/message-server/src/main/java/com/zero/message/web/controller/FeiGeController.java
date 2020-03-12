package com.zero.message.web.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.message.dto.req.SendMsgRequest;
import com.zero.message.dto.resp.FeiGeListResponseVo;
import com.zero.message.feign.MessageServerClient;
import com.zero.message.service.FeiGeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@RestController
@RequestMapping("/feige")
@Api(description = "飞鸽服务controller")
public class FeiGeController implements MessageServerClient {

    @Autowired
    private FeiGeService feiGeService;

    @GetMapping(value = "/list")
    @ApiOperation("查询列表")
    @Override
    public ReturnVo<FeiGeListResponseVo> list() {
        return ReturnVo.success(feiGeService.list());
    }

    @PostMapping(value = "/sendMsg")
    @ApiOperation("发送消息")
    @Override
    public BaseReturnVo sendMsgAlone(@RequestBody SendMsgRequest sendMsgRequest) {
        feiGeService.sendMsgAlone(sendMsgRequest);
        return BaseReturnVo.success();
    }
}
