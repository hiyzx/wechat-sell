package com.zero.order.vo.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

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
