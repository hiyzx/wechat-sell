package com.zero.customer.mq;

import com.zero.customer.vo.message.CommentProductMessage;
import com.zero.customer.vo.message.UserPayMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Component
public class MessageProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendUserPayMessage(UserPayMessage message) {
        send(MessageConstant.USER_PAY_QUEUE, message);
    }

    public void sendCommentProductMessage(CommentProductMessage message) {
        send(MessageConstant.COMMENT_PRODUCT_QUEUE, message);
    }

    private void send(String queue, Object message) {
        jmsMessagingTemplate.convertAndSend(queue, message);
    }
}
