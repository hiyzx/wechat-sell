package com.zero.product.web.controller;

import com.zero.common.po.ProductInfo;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.product.dto.ProductCommentDto;
import com.zero.product.dto.ProductIncreaseSellCountDto;
import com.zero.product.facade.ProductServerFacade;
import com.zero.product.vo.ProductCategoryVo;
import com.zero.product.vo.ProductInfoVo;
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
    private ProductServerFacade productServerFacade;

    @GetMapping("/listCategory")
    @ApiOperation("查询所有的商品类目名称")
    public ReturnVo<List<ProductCategoryVo>> listCategory(@RequestParam Long timestamp,
            @RequestParam String authorization) {
        return ReturnVo.success(productServerFacade.listCategory());
    }

    @GetMapping("/listByCategory")
    @ApiOperation("查询某一类目下的所有商品")
    public ReturnVo<List<ProductInfoVo>> listByCategory(@ApiParam("类目Id") @RequestParam Integer categoryId,
            @RequestParam Long timestamp, @RequestParam String authorization) {
        return ReturnVo.success(productServerFacade.listByCategory(categoryId));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询某一商品")
    public ReturnVo<ProductInfo> get(@ApiParam("商品id") @PathVariable Integer id) {
        return ReturnVo.success(productServerFacade.get(id));
    }

    @PostMapping("/comment")
    @ApiOperation("评论")
    public BaseReturnVo comment(@RequestBody ProductCommentDto productCommentDto) {
        productServerFacade.comment(productCommentDto);
        return BaseReturnVo.success();
    }

	@PostMapping("/increaseSellCount")
	@ApiOperation("增加销量")
	public BaseReturnVo increaseSellCount(@RequestBody ProductIncreaseSellCountDto productIncreaseSellCountDto) {
		productServerFacade.increaseSellCount(productIncreaseSellCountDto);
		return BaseReturnVo.success();
	}
}
