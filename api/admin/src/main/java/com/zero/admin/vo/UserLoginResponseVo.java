package com.zero.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("店家登录返回vo对象")
public class UserLoginResponseVo {

    private String cookieValue;

    private Integer storeId;
}
