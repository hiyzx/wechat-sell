package com.zero.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.ProductCategory;
import com.zero.product.dao.ProductCategoryMapper;
import com.zero.product.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {

    @Override
    public List<ProductCategory> findAllEnable() {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", false).orderByAsc("show_index");
        return this.selectList(queryWrapper);
    }

}
