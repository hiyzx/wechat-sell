package com.zero.admin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.common.vo.product.ProductInfoVo;
import com.zero.product.po.ProductInfo;
import com.zero.product.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/18
 */
@Service
@Slf4j
public class ProductInfoServerFacade {

    @Reference
    private ProductInfoService productInfoService;

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
}
