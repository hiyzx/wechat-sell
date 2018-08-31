package com.zero.admin.web.controller;

import com.zero.admin.annotation.Authorize;
import com.zero.admin.service.ProductInfoServerFacade;
import com.zero.common.vo.ReturnVo;
import com.zero.common.vo.product.ProductInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/18
 */
@RestController
@RequestMapping("/product")
@Api(description = "商品相关接口")
public class ProductInfoController {

    @Resource
    private ProductInfoServerFacade productInfoService;

    @Authorize
    @GetMapping("/listByCategory.json")
    @ApiOperation("查询某一类目下的所有商品")
    public ReturnVo<List<ProductInfoVo>> listByCategory(@RequestParam String sessionId,
            @ApiParam("类目Id") @RequestParam Integer categoryId) {
        return ReturnVo.success(productInfoService.listByCategory(categoryId));
    }
}
