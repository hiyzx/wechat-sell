package com.zero.order.mq;

import com.zero.common.enums.CodeEnum;
import com.zero.common.po.ProductInfo;
import com.zero.common.vo.ReturnVo;
import com.zero.message.dto.req.SendMsgRequest;
import com.zero.message.feign.MessageServerClient;
import com.zero.order.vo.dto.OrderDetailDto;
import com.zero.order.vo.message.UserPayMessage;
import com.zero.product.dto.ProductIncreaseSellCountDto;
import com.zero.product.feign.ProductServerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Component
@Slf4j
public class MessageConsumer {

    @Resource
    private ProductServerClient productServerClient;
    @Resource
    private MessageServerClient messageServerClient;

    @JmsListener(destination = MessageConstant.USER_PAY_QUEUE)
    public void userPayMessageConsume(UserPayMessage userPayMessage) {
        // 调用消息服务发送提示
        messageServerClient.sendMsgAlone(new SendMsgRequest(284, "notice", "新的订单", "有用户下单了!", ""));
        int userId = userPayMessage.getUserId();
        // 列出用户购买的所有商品并增加销量
        List<OrderDetailDto> orderDetailDtos = userPayMessage.getOrderDetailDtos();
        orderDetailDtos.forEach(orderDetailDto -> {
            Integer productId = orderDetailDto.getProductId();
            ReturnVo<ProductInfo> productInfoReturnVo = productServerClient.get(productId);
            if (Objects.equals(productInfoReturnVo.getResCode(), CodeEnum.SUCCESS.getCodeEnum())) {
                ProductInfo productInfo = productInfoReturnVo.getData();
                if (productInfo != null) {
                    int count = orderDetailDto.getCount();
                    int productInfoId = productInfo.getId();
                    productServerClient.increaseSellCount(new ProductIncreaseSellCountDto(productId, count));
                    log.info("openid={} pay success and modify the sellCount={} of productInfoId={}", userId, count,
                            productInfoId);
                }
            }
        });
        log.info("consume userId={} pay message", userId);
    }
}
