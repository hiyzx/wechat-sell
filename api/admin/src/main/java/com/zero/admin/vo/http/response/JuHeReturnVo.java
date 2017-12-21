package com.zero.admin.vo.http.response;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/12/21
 */
@Data
public class JuHeReturnVo<T> {

    private String resultcode;

    private String reason;

    private T result;

    private Integer error_code;
}
