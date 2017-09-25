package com.zero.customer.vo;

import com.zero.common.po.User;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/09/25
 */
@Data
public class UserLoginResponseVo {

    private String sessionId;

    private User user;
}
