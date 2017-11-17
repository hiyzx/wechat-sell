package com.zero.customer.vo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublishInfo implements Serializable {

    private String pageCount;

    private String sizeInBytes;

    private String publishTime;
}