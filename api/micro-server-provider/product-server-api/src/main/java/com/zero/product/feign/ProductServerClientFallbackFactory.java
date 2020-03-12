package com.zero.product.feign;

import com.zero.common.po.ProductInfo;
import com.zero.common.util.JsonHelper;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.product.dto.ProductCommentDto;
import com.zero.product.dto.ProductDecreaseStockCountDto;
import com.zero.product.dto.ProductIncreaseSellCountDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class ProductServerClientFallbackFactory implements FallbackFactory<ProductServerClient> {

    @Override
    public ProductServerClient create(Throwable throwable) {
        return new ProductServerClient() {
            @Override
            public BaseReturnVo comment(ProductCommentDto productCommentDto) {
                log.error("请求comment方法进入回退逻辑,请求参数:{}", JsonHelper.toJSon(productCommentDto));
                return BaseReturnVo.fail();
            }

            @Override
            public ReturnVo<ProductInfo> get(Integer id) {
                log.error("请求get方法进入回退逻辑,请求参数:{}", JsonHelper.toJSon(id));
                return ReturnVo.fail(null );
            }

            @Override
            public BaseReturnVo increaseSellCount(ProductIncreaseSellCountDto productIncreaseSellCountDto) {
                log.error("请求increaseSellCount方法进入回退逻辑,请求参数:{}", JsonHelper.toJSon(productIncreaseSellCountDto));
                return BaseReturnVo.fail();
            }

            @Override
            public BaseReturnVo decreaseStockCount(List<ProductDecreaseStockCountDto> productDecreaseStockCountDtos) {
                log.error("请求decreaseStockCount方法进入回退逻辑,请求参数:{}", JsonHelper.toJSon(productDecreaseStockCountDtos));
                return BaseReturnVo.fail();
            }

        };
    }
}