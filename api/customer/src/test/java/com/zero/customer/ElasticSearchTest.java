package com.zero.customer;

import com.zero.common.dao.ProductInfoMapper;
import com.zero.common.po.ProductInfo;
import com.zero.common.vo.dto.ProductInfoEsDto;
import com.zero.customer.es.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/12/20
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticSearchTest {

    @Resource
    private ProductInfoRepository productInfoRepository;
    @Resource
    private ProductInfoMapper productInfoMapper;

    @Test
    public void testInit() {
        List<ProductInfo> productInfos = productInfoMapper.selectAll();
        List<ProductInfoEsDto> productInfoEsDtos = new ArrayList<>(productInfos.size());
        productInfos.forEach(productInfo -> {
            ProductInfoEsDto esDto = ProductInfoEsDto.createEsDto(productInfo);
            productInfoEsDtos.add(esDto);
        });
        productInfoRepository.save(productInfoEsDtos);
    }

    @Test
    public void testGet() {

    }
}
