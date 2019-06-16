package com.zero.product.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author zero
 * @date 2017/08/17
 */
public interface ProductInfoExtMapper {

    void increaseSellCount(@Param("productInfoId") Integer productInfoId, @Param("count") Integer count);

    void decreaseStockCount(@Param("productInfoId") Integer productInfoId, @Param("count") Integer count);
}
