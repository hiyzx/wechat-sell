package com.zero.admin.enums;

import com.zero.common.enums.StringEnum;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
public enum AdminCodeEnum implements StringEnum {

    ;

    private String codeEnum;

    private AdminCodeEnum(String value) {
        this.codeEnum = value;
    }

    @Override
    public String getCodeEnum() {
        return codeEnum;
    }
}
