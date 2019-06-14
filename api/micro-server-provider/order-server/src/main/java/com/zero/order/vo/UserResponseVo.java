package com.zero.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yezhaoxing
 * @date 2017/09/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户基本信息vo对象")
public class UserResponseVo {

    private Integer id;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

}
