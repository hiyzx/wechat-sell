package com.zero.order.vo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yezhaoxing
 * @date 2017/11/13
 */
@Data
@ToString
public class MessageBody implements Serializable {

    private String documentId;

    private String title;

    private String status;

    private PublishInfo publishInfo;

    private Error error;

    private String notifyTime;
}
