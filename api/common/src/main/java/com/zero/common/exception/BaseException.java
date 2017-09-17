package com.zero.common.exception;


import com.zero.common.enums.CodeEnum;

/**
 * @description 自定义异常
 * @author yezhaoxing
 * @date 2017/4/29
 */
public class BaseException extends Exception {

    private CodeEnum codeEnum;

    private String msg;

    public BaseException(CodeEnum codeEnum, String msg) {
        super(msg);
        this.codeEnum = codeEnum;
        this.msg = msg;
    }

    public CodeEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
