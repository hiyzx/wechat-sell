package com.zero.product.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.common.po.ProductCategory;
import com.zero.common.po.ProductComment;
import com.zero.common.po.ProductInfo;
import com.zero.common.po.Store;
import com.zero.common.util.DateHelper;
import com.zero.common.util.NumberUtil;
import com.zero.product.dto.ProductCommentDto;
import com.zero.product.dto.ProductIncreaseSellCountDto;
import com.zero.product.service.ProductCategoryService;
import com.zero.product.service.ProductCommentService;
import com.zero.product.service.ProductInfoService;
import com.zero.product.service.StoreService;
import com.zero.product.vo.ProductCategoryVo;
import com.zero.product.vo.ProductInfoVo;
import com.zero.product.vo.StoreInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCommentService productCommentService;

    public StoreInfoVo getStoreInfo() {
        Store store = storeService.selectOne(new QueryWrapper<>());
        StoreInfoVo rtn = new StoreInfoVo();
        if (store != null) {
            BeanUtils.copyProperties(store, rtn);
        }
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

    public ProductInfo get(Integer id) {
        return productInfoService.selectById(id);
    }

    // 评论
    public void comment(ProductCommentDto productCommentDto) {
        // 保存一条评论记录
        Integer userId = productCommentDto.getUserId();
        ProductComment productComment = new ProductComment();
        productComment.setUserId(userId);
        productComment.setScore(productCommentDto.getScore());
        productComment.setProductId(productCommentDto.getProductId());
        productComment.setContent(productCommentDto.getContent());
        productComment.setCreateTime(DateHelper.getCurrentDateTime());
        productComment.setIsDelete(false);
        productCommentService.insert(productComment);
        // 更新商品平均数,评论数等
        commentProductMessageConsume(productComment.getProductId(), productComment.getScore());
        log.info("userId={} comment productCommentDto={}", userId, productCommentDto.toString());
    }

    public void increaseSellCount(ProductIncreaseSellCountDto productIncreaseSellCountDto) {
        productInfoService.increaseSellCount(productIncreaseSellCountDto.getProductId(),
                productIncreaseSellCountDto.getCount());
    }

    // 更新商品平均数,评论数等
    private void commentProductMessageConsume(Integer productId, Integer score) {
        ProductInfo productInfo = productInfoService.selectById(productId);
        ProductInfo tmp = new ProductInfo();
        tmp.setId(productId);
        tmp.setTotalScore(productInfo.getTotalScore() + score);
        tmp.setCommentCount(productInfo.getCommentCount() + 1);
        tmp.setAverageScore(NumberUtil.div(tmp.getTotalScore(), tmp.getCommentCount()));
        productInfoService.updateById(tmp);
        log.info("consume comment productId={}'s message", productId);
    }
}
