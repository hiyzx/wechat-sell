package com.zero.customer.vo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yezhaoxing
 * @date 2017/11/13
 */
@Data
@ToString
public class NotificationDto implements Serializable {

    private String messageId;

    private String messageBody;

    private String notification;

    private String server;
}
