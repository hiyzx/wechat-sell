package com.zero.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.ProductCategory;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<ProductCategory> findAllEnable();
}
