package com.zero.common.exception;

import com.zero.common.enums.StringEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 自定义异常
 * @author yezhaoxing
 * @date 2017/4/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends Exception {

    private StringEnum codeEnum;

    private String msg;

    public BaseException(StringEnum codeEnum, String msg) {
        super(msg);
        this.codeEnum = codeEnum;
        this.msg = msg;
    }
}
