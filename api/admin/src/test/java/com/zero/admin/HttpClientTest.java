package com.zero.admin;

import com.zero.admin.util.JuHeUtil;
import com.zero.product.dao.ProductCategoryMapper;
import com.zero.product.dao.ProductInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HttpClientTest {

    @Resource
    private JuHeUtil juHeUtil;
    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;

    @Test
    public void testInsertCategories() {
        productCategoryMapper.insertList(juHeUtil.getCategories());
    }

    @Test
    public void testInsertProducts() {
        productInfoMapper.insertList(juHeUtil.getProducts(17));
    }
}
