package com.zero.order.vo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Error implements Serializable {

    private String code;

    private String message;
}