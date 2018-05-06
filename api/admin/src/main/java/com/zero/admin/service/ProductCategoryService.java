package com.zero.admin.service;

import com.zero.common.util.DateHelper;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.product.dao.ProductCategoryMapper;
import com.zero.product.po.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/11/08
 */
@Service
@Slf4j
public class ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategoryVo> list() {
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

    public void add(Integer userId, String categoryName) {
        Condition condition = new Condition(ProductCategory.class);
        condition.orderBy("showIndex").desc();
        List<ProductCategory> productCategories = productCategoryMapper.selectByExampleAndRowBounds(condition,
                new RowBounds(0, 1));
        ProductCategory lastCategory = productCategories.get(0);
        ProductCategory tmp = new ProductCategory();
        tmp.setName(categoryName);
        tmp.setShowIndex(lastCategory.getShowIndex() + 1);
        tmp.setCreateTime(DateHelper.getCurrentDateTime());
        tmp.setIsDelete(false);
        productCategoryMapper.insert(tmp);
        log.info("userId={} add category name={}", userId, categoryName);
    }

    public void edit(Integer userId, Integer categoryId, String categoryName) {
        ProductCategory tmp = new ProductCategory();
        tmp.setId(categoryId);
        tmp.setName(categoryName);
        tmp.setUpdateTime(DateHelper.getCurrentDateTime());
        productCategoryMapper.updateByPrimaryKeySelective(tmp);
        log.info("userId={} edit category id={} name={}", userId, categoryId, categoryName);
    }

    public void delete(Integer userId, Integer categoryId) {
        ProductCategory tmp = new ProductCategory();
        tmp.setId(categoryId);
        tmp.setUpdateTime(DateHelper.getCurrentDateTime());
        tmp.setIsDelete(true);
        productCategoryMapper.updateByPrimaryKeySelective(tmp);
        log.info("userId={} delete category id={}", userId, categoryId);
    }
}
