package com.zero.admin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.common.util.DateHelper;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.product.po.ProductCategory;
import com.zero.product.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/11/08
 */
@Service
@Slf4j
public class ProductCategoryServerFacade {

    @Reference
    private ProductCategoryService productCategoryService;

    public List<ProductCategoryVo> list() {
        List<ProductCategory> productCategories = productCategoryService.findAllEnable();
        List<ProductCategoryVo> rtn = new ArrayList<>(productCategories.size());
        productCategories.forEach(productCategory -> {
            ProductCategoryVo tmp = new ProductCategoryVo();
            BeanUtils.copyProperties(productCategory, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public void add(Integer userId, String categoryName) {
        ProductCategory lastCategory = productCategoryService.findNew();
        ProductCategory tmp = new ProductCategory();
        tmp.setName(categoryName);
        tmp.setShowIndex(lastCategory.getShowIndex() + 1);
        tmp.setCreateTime(DateHelper.getCurrentDateTime());
        tmp.setIsDelete(false);
        productCategoryService.save(tmp);
        log.info("userId={} add category name={}", userId, categoryName);
    }

    public void edit(Integer userId, Integer categoryId, String categoryName) {
        ProductCategory tmp = new ProductCategory();
        tmp.setId(categoryId);
        tmp.setName(categoryName);
        tmp.setUpdateTime(DateHelper.getCurrentDateTime());
        productCategoryService.update(tmp);
        log.info("userId={} edit category id={} name={}", userId, categoryId, categoryName);
    }

    public void delete(Integer userId, Integer categoryId) {
        ProductCategory tmp = new ProductCategory();
        tmp.setId(categoryId);
        tmp.setUpdateTime(DateHelper.getCurrentDateTime());
        tmp.setIsDelete(true);
        productCategoryService.update(tmp);
        log.info("userId={} delete category id={}", userId, categoryId);
    }
}
