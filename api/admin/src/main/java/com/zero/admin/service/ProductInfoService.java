package com.zero.admin.service;

import com.zero.common.vo.product.ProductInfoVo;
import com.zero.product.dao.ProductCategoryMapper;
import com.zero.product.dao.ProductCommentMapper;
import com.zero.product.dao.ProductInfoMapper;
import com.zero.product.po.ProductInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductInfoService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private ProductCommentMapper productCommentMapper;

    public ProductInfo getByProductUid(String productInfoUid) {
        Condition condition = new Condition(ProductInfo.class);
        condition.createCriteria().andEqualTo("uid", productInfoUid);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(condition);
        return productInfos.isEmpty() ? null : productInfos.get(0);
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
