package com.zero.customer.enums;

import com.zero.common.enums.StringEnum;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
public enum CustomerCodeEnum implements StringEnum {

    /**
     * 商品不存在
     */
    PRODUCT_NOT_EXIST("200001"),

    HAS_PAY("200002"),

    NOT_NEW_ORDER("200003"),

    CAPTCHA_WRONG("200004"),

    CHECK_REPEAT("200005");
    private String codeEnum;

    private CustomerCodeEnum(String value) {
        this.codeEnum = value;
    }

    @Override
    public String getCodeEnum() {
        return codeEnum;
    }
}
