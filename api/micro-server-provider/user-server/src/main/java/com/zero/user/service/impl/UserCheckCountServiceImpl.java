package com.zero.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.UserCheckCount;
import com.zero.user.dao.UserCheckCountMapper;
import com.zero.user.service.UserCheckCountService;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
public class UserCheckCountServiceImpl extends ServiceImpl<UserCheckCountMapper, UserCheckCount>
        implements UserCheckCountService {

    @Override
    public UserCheckCount getByUserId(Integer userId) {
        QueryWrapper<UserCheckCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.selectOne(queryWrapper);
    }
}
