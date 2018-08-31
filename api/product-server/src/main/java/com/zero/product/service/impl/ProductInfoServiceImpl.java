package com.zero.product.service.impl;

import com.zero.product.dao.ProductInfoMapper;
import com.zero.product.dao.ext.ProductInfoExtMapper;
import com.zero.product.po.ProductInfo;
import com.zero.product.service.ProductInfoService;
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
public class ProductInfoServiceImpl extends AbstractService<ProductInfo> implements ProductInfoService {
    @Resource
    private ProductInfoMapper productInfoMapper;

    @Resource
    private ProductInfoExtMapper productInfoExtMapper;

    public void increaseSellCount(Integer productInfoId, Integer count) {
        productInfoExtMapper.increaseSellCount(productInfoId, count);
    }

    @Override
    public List<ProductInfo> findByCategoryId(Integer categoryId) {
        Condition condition = new Condition(ProductInfo.class);
        condition.createCriteria().andEqualTo("categoryId", categoryId).andEqualTo("isDelete", false);
        return productInfoMapper.selectByCondition(condition);
    }
}
