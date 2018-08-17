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

    PHONE_NOT_EXIST("100006"),

    PASSWORD_NOT_CONSISTENT("100007"),

    ORDER_NOT_FOUND("100008"),

    /**
     * 参数校验失败
     */
    VALID_FAIL("100009"),

    TOKEN_SOON_EXPIRE("100010"),

    REQUEST_TIME_OUT("100011"),

    AUTHORIZATION_FAIL("100012"),

    REQUEST_REPEAT("100013"),

    SUCCESS("000000"),

    QUARTZ_EXCEPTION("100014");
    private String codeEnum;

    private CodeEnum(String value) {
        this.codeEnum = value;
    }

    @Override
    public String getCodeEnum() {
        return codeEnum;
    }

}
