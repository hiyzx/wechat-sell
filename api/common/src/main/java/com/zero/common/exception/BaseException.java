package com.zero.common.exception;


import com.zero.common.enums.StringEnum;

/**
 * @description 自定义异常
 * @author yezhaoxing
 * @date 2017/4/29
 */
public class BaseException extends Exception {

    private StringEnum codeEnum;

    private String msg;

    public BaseException(StringEnum codeEnum, String msg) {
        super(msg);
        this.codeEnum = codeEnum;
        this.msg = msg;
    }

    public StringEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(StringEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
