package com.zero.product.service.impl;

import com.zero.product.dao.ProductCategoryMapper;
import com.zero.product.po.ProductCategory;
import com.zero.product.service.ProductCategoryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class ProductCategoryServiceImpl extends AbstractService<ProductCategory> implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> findAllEnable() {
        Condition condition = new Condition(ProductCategory.class);
        condition.createCriteria().andEqualTo("isDelete", false);
        condition.orderBy("show_index");
        return productCategoryMapper.selectByCondition(condition);
    }

    @Override
    public ProductCategory findNew() {
        Condition condition = new Condition(ProductCategory.class);
        condition.orderBy("showIndex").desc();
        List<ProductCategory> productCategories = productCategoryMapper.selectByExampleAndRowBounds(condition,
                new RowBounds(0, 1));
        return productCategories.get(0);
    }
}
