package com.zero.customer.web.controller;

import com.zero.common.vo.ReturnVo;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.common.vo.product.ProductInfoVo;
import com.zero.customer.service.ProductInfoService;
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
    private ProductInfoService productInfoService;

    @GetMapping("/listCategory.json")
    @ApiOperation("查询所有的商品类目名称")
    public ReturnVo<List<ProductCategoryVo>> listCategory() {
        return ReturnVo.success(productInfoService.listCategory());
    }

    @GetMapping("/listByCategory.json")
    @ApiOperation("查询某一类目下的所有商品")
    public ReturnVo<List<ProductInfoVo>> listByCategory(@ApiParam("类目Id") @RequestParam Integer categoryId) {
        return ReturnVo.success(productInfoService.listByCategory(categoryId));
    }
}
