package com.zero.product.service;

import com.zero.product.common.BaseService;
import com.zero.product.po.ProductCategory;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> findAllEnable();

    ProductCategory findNew();
}
