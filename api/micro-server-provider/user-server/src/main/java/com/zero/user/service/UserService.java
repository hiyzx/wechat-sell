package com.zero.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.User;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserService extends IService<User> {

    boolean existPhone(String phone);

    User selectByPhone(String phone);

    User queryByPhoneAndPassword(String phone, String password);
}
