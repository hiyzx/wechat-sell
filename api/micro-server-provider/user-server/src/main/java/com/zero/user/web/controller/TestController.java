package com.zero.user.web.controller;

import com.zero.common.vo.BaseReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@RestController
@Api("用户登录相关接口")
public class TestController {

    @GetMapping(value = "/hello")
    @ApiOperation("测试")
    public BaseReturnVo service() throws IOException {
        long timeMillis = System.currentTimeMillis();
        if(timeMillis %2 == 0){
            throw new RuntimeException("");
        }else{
            return BaseReturnVo.success();
        }
    }
}
