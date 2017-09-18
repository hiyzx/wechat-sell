package com.zero.customer.service;

import com.zero.common.dao.ProductCategoryMapper;
import com.zero.common.dao.ProductInfoMapper;
import com.zero.common.po.ProductCategory;
import com.zero.common.po.ProductInfo;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.common.vo.product.ProductInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/18
 */
@Service
public class ProductInfoService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;

    public List<ProductCategoryVo> listCategory() {
        List<ProductCategory> productCategories = productCategoryMapper.selectAll();
        List<ProductCategoryVo> rtn = new ArrayList<>(productCategories.size());
        productCategories.forEach(productCategory -> {
            ProductCategoryVo tmp = new ProductCategoryVo();
            BeanUtils.copyProperties(productCategory, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public List<ProductInfoVo> listByCategory(Integer categoryId) {
        Condition condition = new Condition(ProductInfo.class);
        condition.createCriteria().andEqualTo("categoryId", categoryId);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(condition);
        List<ProductInfoVo> rtn = new ArrayList<>(productInfos.size());
        productInfos.forEach(productInfo -> {
            ProductInfoVo tmp = new ProductInfoVo();
            BeanUtils.copyProperties(productInfo, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }
}
