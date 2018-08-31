package com.zero.user.service;

import com.zero.user.common.BaseService;
import com.zero.user.po.UserCheckCount;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserCheckCountService extends BaseService<UserCheckCount> {

    UserCheckCount getByUserId(Integer userId);
}
