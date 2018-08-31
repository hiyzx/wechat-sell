package com.zero.user.service.impl;

import com.zero.user.dao.UserMapper;
import com.zero.user.po.User;
import com.zero.user.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean existPhone(String phone) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("phone", phone);
        return userMapper.selectCountByExample(condition) > 0;
    }

    @Override
    public User queryByPhoneAndPassword(String phone, String password) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("phone", phone).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(condition);
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }
    }
}
