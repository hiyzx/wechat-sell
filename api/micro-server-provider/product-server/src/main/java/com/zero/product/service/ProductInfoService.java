package com.zero.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.ProductInfo;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface ProductInfoService extends IService<ProductInfo> {

    void increaseSellCount(Integer productInfoId, Integer count);

    List<ProductInfo> findByCategoryId(Integer categoryId);

    void decreaseStockCount(Integer productId, Integer count);
}
