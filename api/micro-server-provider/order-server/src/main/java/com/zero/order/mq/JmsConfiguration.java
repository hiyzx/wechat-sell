package com.zero.order.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Configuration
@EnableJms
public class JmsConfiguration {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        return new MyMessageConverter();
    }

    public class MyMessageConverter implements MessageConverter {
        @Override
        public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
            return session.createObjectMessage((Serializable) object);
        }

        @Override
        public Object fromMessage(Message message) throws JMSException, MessageConversionException {
            ObjectMessage objMessage = (ObjectMessage) message;
            return objMessage.getObject();
        }
    }
}