package com.zero.customer.vo;

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
public class UserLoginResponseVo {

    private String cookieValue;

    private UserResponseVo user;
}
