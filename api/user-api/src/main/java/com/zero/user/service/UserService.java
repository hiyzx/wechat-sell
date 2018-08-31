package com.zero.user.service;

import com.zero.user.common.BaseService;
import com.zero.user.po.User;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserService extends BaseService<User> {

    boolean existPhone(String phone);

    User queryByPhoneAndPassword(String phone, String password);
}
