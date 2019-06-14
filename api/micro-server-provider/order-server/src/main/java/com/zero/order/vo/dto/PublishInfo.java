package com.zero.order.vo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PublishInfo implements Serializable {

    private String pageCount;

    private String sizeInBytes;

    private String publishTime;
}