package com.zero.common.enums;

/**
 * 状态码
 *
 * @author yezhaoxing
 * @date 2017/4/29
 */
public enum CodeEnum implements StringEnum {

    /**
     * 用户未登录
     */
    NOT_LOGIN("403"),

    PARAM_NOT_MATCH("400"),

    PAGE_NOT_FOUND("404"),

    INTERNAL_SERVER_ERROR("500"),

    OFTEN_SEND_MSG("100001"),

    CODE_HAS_EXPIRE("100002"),

    CODE_IS_WRONG("100003"),

    LOGIN_FALL("100004"),


    PHONE_HAS_EXIST("100005"),

    /**
     * 参数校验失败
     */
    VALID_FAIL("100006"),

    SUCCESS("000000");
    private String CodeEnum;

    private CodeEnum(String value) {
        this.CodeEnum = value;
    }

    public String getCodeEnum() {
        return CodeEnum;
    }

}
