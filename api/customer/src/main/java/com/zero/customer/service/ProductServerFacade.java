package com.zero.customer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.common.util.DateHelper;
import com.zero.common.vo.product.ProductCategoryVo;
import com.zero.common.vo.product.ProductInfoVo;
import com.zero.customer.vo.StoreInfoVo;
import com.zero.customer.vo.dto.ProductCommentDto;
import com.zero.product.po.ProductCategory;
import com.zero.product.po.ProductComment;
import com.zero.product.po.ProductInfo;
import com.zero.product.po.Store;
import com.zero.product.service.ProductCategoryService;
import com.zero.product.service.ProductCommentService;
import com.zero.product.service.ProductInfoService;
import com.zero.product.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@Service
@Slf4j
public class ProductServerFacade {

    @Reference
    private StoreService storeService;
    @Reference
    private ProductCategoryService productCategoryService;
    @Reference
    private ProductInfoService productInfoService;
    @Reference
    private ProductCommentService productCommentService;

    public StoreInfoVo getStoreInfo() {
        List<Store> stores = storeService.findAll();
        Store store = stores.get(0);
        StoreInfoVo rtn = new StoreInfoVo();
        BeanUtils.copyProperties(store, rtn);
        return rtn;
    }

    public List<ProductCategoryVo> listCategory() {
        List<ProductCategory> productCategories = productCategoryService.findAllEnable();
        List<ProductCategoryVo> rtn = new ArrayList<>(productCategories.size());
        productCategories.forEach(productCategory -> {
            ProductCategoryVo tmp = new ProductCategoryVo();
            BeanUtils.copyProperties(productCategory, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public List<ProductInfoVo> listByCategory(Integer categoryId) {
        List<ProductInfo> productInfos = productInfoService.findByCategoryId(categoryId);
        List<ProductInfoVo> rtn = new ArrayList<>(productInfos.size());
        productInfos.forEach(productInfo -> {
            ProductInfoVo tmp = new ProductInfoVo();
            BeanUtils.copyProperties(productInfo, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public void comment(Integer userId, ProductCommentDto productCommentDto) {
        ProductComment productComment = new ProductComment();
        productComment.setUserId(userId);
        productComment.setScore(productCommentDto.getScore());
        productComment.setProductId(productCommentDto.getProductId());
        productComment.setContent(productCommentDto.getContent());
        productComment.setCreateTime(DateHelper.getCurrentDateTime());
        productComment.setIsDelete(false);
        productCommentService.save(productComment);
        log.info("userId={} comment productCommentDto={}", userId, productCommentDto.toString());
    }
}
