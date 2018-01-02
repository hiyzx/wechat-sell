package com.zero.customer;

import com.zero.common.dao.ProductInfoMapper;
import com.zero.customer.es.ProductInfoRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    /*@Test
    public void testInit() {
        PageHelper.startPage(2, 30, false);
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
        Page<ProductInfoEsDto> productInfos = productInfoRepository.findByNameLikeOrInfoLikeOrDescriptionLike("青少年",
                "青少年", "青少年", new PageRequest(1, 5));
        System.out.println(123);
    }*/
}
