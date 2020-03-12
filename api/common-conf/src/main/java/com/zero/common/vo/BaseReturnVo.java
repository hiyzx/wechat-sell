package com.zero.common.vo;

import com.zero.common.enums.CodeEnum;
import com.zero.common.enums.StringEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础的返回类
 *
 * @author yezhaoxing
 * @date 2017/4/29
 */
@ApiModel("基础返回vo对象")
@Data
public class BaseReturnVo {

    final static String SUCCESS_DEFAULT_DESC = "success";
    final static String FAIL_DEFAULT_DESC = "fail";
    @ApiModelProperty("返回码")
    private String resCode;
    @ApiModelProperty("返回说明")
    private String resDes;

    public BaseReturnVo() {
    }

    public BaseReturnVo(StringEnum codeEnum, String msg) {
        this.resCode = codeEnum.getCodeEnum();
        this.resDes = msg;
    }

    public static BaseReturnVo success() {
        return new BaseReturnVo(CodeEnum.SUCCESS, SUCCESS_DEFAULT_DESC);
    }

    public static BaseReturnVo fail() {
        return new BaseReturnVo(CodeEnum.INTERNAL_SERVER_ERROR, FAIL_DEFAULT_DESC);
    }
}
