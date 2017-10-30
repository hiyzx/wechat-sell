package com.zero.customer.mq;

import com.zero.common.dao.ext.ProductInfoExtMapper;
import com.zero.common.po.ProductInfo;
import com.zero.customer.service.ProductInfoService;
import com.zero.customer.vo.dto.OrderDetailDto;
import com.zero.customer.vo.message.UserPayMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Component
@Slf4j
public class MessageConsumer {

    @Resource
    private ProductInfoExtMapper productInfoExtMapper;
    @Resource
    private ProductInfoService productInfoService;

    @JmsListener(destination = MessageConstant.USER_PAY_QUEUE)
    public void userPayMessageConsume(UserPayMessage userPayMessage) throws JMSException {
        int userId = userPayMessage.getUserId();
        List<OrderDetailDto> orderDetailDtos = userPayMessage.getOrderDetailDtos();
        orderDetailDtos.forEach(orderDetailDto -> {
            String productInfoUid = orderDetailDto.getProductInfoUid();
            ProductInfo productInfo = productInfoService.getByProductUid(productInfoUid);
            if (productInfo != null) {
                int count = orderDetailDto.getCount();
                int productInfoId = productInfo.getId();
                productInfoExtMapper.increaseSellCount(productInfoId, count);
                log.info("openid={} pay success and modify the sellCount={} of productInfoId={}", userId, count,
                        productInfoId);
            }
        });
        log.info("consume userId={} pay message", userId);
    }
}
