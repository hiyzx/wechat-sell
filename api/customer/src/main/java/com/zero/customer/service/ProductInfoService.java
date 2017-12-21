package com.zero.customer.service;

import com.zero.common.dao.ProductCategoryMapper;
import com.zero.common.dao.ProductCommentMapper;
import com.zero.common.dao.ProductInfoMapper;
import com.zero.common.po.ProductCategory;
import com.zero.common.po.ProductComment;
import com.zero.common.po.ProductInfo;
import com.zero.common.util.DateHelper;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.common.vo.product.ProductInfoVo;
import com.zero.customer.vo.dto.ProductCommentDto;
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

    public List<ProductCategoryVo> listCategory() {
        Condition condition = new Condition(ProductCategory.class);
        condition.createCriteria().andEqualTo("isDelete", false);
        condition.orderBy("show_index");
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(condition);
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
        condition.createCriteria().andEqualTo("categoryId", categoryId).andEqualTo("isDelete", false);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(condition);
        List<ProductInfoVo> rtn = new ArrayList<>(productInfos.size());
        productInfos.forEach(productInfo -> {
            ProductInfoVo tmp = new ProductInfoVo();
            BeanUtils.copyProperties(productInfo, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public void comment(Integer userId, ProductCommentDto productCommentDto) {
        ProductComment rtn = new ProductComment();
        rtn.setUserId(userId);
        rtn.setScore(productCommentDto.getScore());
        rtn.setProductId(productCommentDto.getProductId());
        rtn.setContent(productCommentDto.getContent());
        rtn.setCreateTime(DateHelper.getCurrentDateTime());
        rtn.setIsDelete(false);
        productCommentMapper.insert(rtn);
        log.info("userId={} comment productCommentDto={}", userId, productCommentDto.toString());
    }
}
