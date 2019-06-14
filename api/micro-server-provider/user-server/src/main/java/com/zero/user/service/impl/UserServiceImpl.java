package com.zero.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.User;
import com.zero.user.dao.UserMapper;
import com.zero.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean existPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return this.selectCount(queryWrapper) > 0;
    }

    @Override
    public User selectByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return this.selectOne(queryWrapper);
    }

    @Override
    public User queryByPhoneAndPassword(String phone, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone).eq("password", password);
        return this.selectOne(queryWrapper);
    }
}
