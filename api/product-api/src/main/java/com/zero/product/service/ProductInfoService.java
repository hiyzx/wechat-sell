package com.zero.product.service;

import com.zero.product.common.BaseService;
import com.zero.product.po.ProductInfo;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface ProductInfoService extends BaseService<ProductInfo> {

    void increaseSellCount(Integer productInfoId, Integer count);

    List<ProductInfo> findByCategoryId(Integer categoryId);
}
