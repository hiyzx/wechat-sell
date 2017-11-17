package com.zero.customer.vo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Error implements Serializable {

    private String code;

    private String message;
}