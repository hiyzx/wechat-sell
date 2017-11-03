package com.zero.customer.web.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.common.vo.product.ProductInfoVo;
import com.zero.customer.service.ProductInfoService;
import com.zero.customer.util.SessionHelper;
import com.zero.customer.vo.dto.ProductCommentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private SessionHelper sessionHelper;

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

    @PostMapping("/comment.json")
    @ApiOperation("评论商品")
    public BaseReturnVo comment(@RequestParam String sessionId, @RequestBody ProductCommentDto productCommentDto) {
        productInfoService.comment(sessionHelper.getUserId(sessionId), productCommentDto);
        return BaseReturnVo.success();
    }
}
