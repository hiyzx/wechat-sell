package com.zero.user.vo;

import io.swagger.annotations.ApiModel;
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
@ApiModel("用户登录返回vo对象")
public class UserLoginResponseVo {

    private String cookieValue;

    private UserResponseVo user;
}
