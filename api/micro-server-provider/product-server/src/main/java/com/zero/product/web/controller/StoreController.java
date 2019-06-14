package com.zero.product.web.controller;

import com.zero.common.vo.ReturnVo;
import com.zero.product.facade.ProductServerFacade;
import com.zero.product.vo.StoreInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/10/23
 */
@RestController
@RequestMapping("/store")
@Api(description = "商家相关接口")
public class StoreController {

    @Resource
    private ProductServerFacade productServerFacade;

    @GetMapping("/info")
    @ApiOperation("查询商家列表")
    public ReturnVo<StoreInfoVo> getStoreInfo(@RequestParam Long timestamp, @RequestParam String authorization) {
        return ReturnVo.success(productServerFacade.getStoreInfo());
    }
}
