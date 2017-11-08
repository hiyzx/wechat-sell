package com.zero.admin.web.controller;

import com.zero.admin.annotation.Authorize;
import com.zero.admin.service.ProductCategoryService;
import com.zero.admin.util.SessionHelper;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.common.vo.product.ProductCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/11/08
 */
@RestController
@RequestMapping("/product/category")
@Api(description = "分类相关接口")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private SessionHelper sessionHelper;

    @Authorize
    @GetMapping("/list.json")
    @ApiOperation("查询所有的商品类目名称")
    public ReturnVo<List<ProductCategoryVo>> list(@RequestParam String sessionId) {
        return ReturnVo.success(productCategoryService.list());
    }

    @Authorize
    @PostMapping("/add.json")
    @ApiOperation("添加分类")
    public BaseReturnVo add(@RequestParam String sessionId,
            @ApiParam(value = "名称", required = true) @RequestParam String categoryName) {
        Integer userId = sessionHelper.getUserId(sessionId);
        productCategoryService.add(userId, categoryName);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/edit.json")
    @ApiOperation("编辑分类")
    public BaseReturnVo edit(@RequestParam String sessionId,
            @ApiParam(value = "分类id", required = true) @RequestParam Integer categoryId,
            @ApiParam(value = "名称", required = true) @RequestParam String categoryName) {
        Integer userId = sessionHelper.getUserId(sessionId);
        productCategoryService.edit(userId, categoryId, categoryName);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/delete.json")
    @ApiOperation("删除分类")
    public BaseReturnVo delete(@RequestParam String sessionId,
            @ApiParam(value = "分类id", required = true) @RequestParam Integer categoryId) {
        Integer userId = sessionHelper.getUserId(sessionId);
        productCategoryService.delete(userId, categoryId);
        return BaseReturnVo.success();
    }
}
