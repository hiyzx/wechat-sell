package com.zero.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.zero.common.po.ProductInfo;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.product.dto.ProductCommentDto;
import com.zero.product.dto.ProductIncreaseSellCountDto;

import io.swagger.annotations.ApiParam;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@FeignClient(name = "product-server")
@RequestMapping(value = "/product")
public interface ProductServerClient {

    @PostMapping(value = "/comment")
    BaseReturnVo comment(@RequestBody ProductCommentDto productCommentDto);

    @GetMapping("/{id}")
    ReturnVo<ProductInfo> get(@ApiParam("商品id") @PathVariable("id") Integer id);

    @PostMapping("/increaseSellCount")
    BaseReturnVo increaseSellCount(@RequestBody ProductIncreaseSellCountDto productIncreaseSellCountDto);
}
