package com.zero.message.dto.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Data
@ApiModel("飞鸽返回单个用户信息vo对象")
public class FeiGeListUserInfoVo {

    private Integer id;

    private String name;

    private String remark;
}
