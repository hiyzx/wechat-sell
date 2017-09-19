package com.zero.customer.enums;

import com.zero.common.enums.StringEnum;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
public enum CustomerCodeEnum implements StringEnum{

    PRODUCT_NOT_EXIST("100001");

    private String CodeEnum;

    private CustomerCodeEnum(String value) {
        this.CodeEnum = value;
    }

    public String getCodeEnum() {
        return CodeEnum;
    }
}
