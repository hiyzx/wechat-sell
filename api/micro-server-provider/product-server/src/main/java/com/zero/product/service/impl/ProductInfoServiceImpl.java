package com.zero.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.ProductInfo;
import com.zero.product.dao.ProductInfoMapper;
import com.zero.product.dao.ext.ProductInfoExtMapper;
import com.zero.product.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Resource
    private ProductInfoExtMapper productInfoExtMapper;

    public void increaseSellCount(Integer productInfoId, Integer count) {
        productInfoExtMapper.increaseSellCount(productInfoId, count);
    }

    @Override
    public List<ProductInfo> findByCategoryId(Integer categoryId) {
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).eq("is_delete", false);
        return this.selectList(queryWrapper);
    }
}
