package com.zero.order.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Component
public class MessageQueue {

    @Bean(name = "userPayQueue")
    public Queue userPayQueue() {
        return new ActiveMQQueue(MessageConstant.USER_PAY_QUEUE);
    }

    @Bean(name = "commentProductQueue")
    public Queue commentProductQueue() {
        return new ActiveMQQueue(MessageConstant.COMMENT_PRODUCT_QUEUE);
    }
}
