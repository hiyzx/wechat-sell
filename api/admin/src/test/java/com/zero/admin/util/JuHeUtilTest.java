package com.zero.admin.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.product.service.ProductCategoryService;
import com.zero.product.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @since 2018/08/31
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JuHeUtilTest {

    @Resource
    private JuHeUtil juHeUtil;
    @Reference
    private ProductCategoryService productCategoryService;
    @Reference
    private ProductInfoService productInfoService;

    @Test
    public void generateQrCode() throws IOException {
        juHeUtil.generateQrCode("hello");
    }

    @Test
    public void testInsertCategories() {
        productCategoryService.save(juHeUtil.getCategories());
    }

    @Test
    public void testInsertProducts() {
        productInfoService.save(juHeUtil.getProducts(17));
    }
}