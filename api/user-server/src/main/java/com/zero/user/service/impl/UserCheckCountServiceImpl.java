package com.zero.user.service.impl;

import com.zero.user.dao.UserCheckCountMapper;
import com.zero.user.po.UserCheckCount;
import com.zero.user.service.UserCheckCountService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class UserCheckCountServiceImpl extends AbstractService<UserCheckCount> implements UserCheckCountService {

    @Resource
    private UserCheckCountMapper userCheckCountMapper;

    @Override
    public UserCheckCount getByUserId(Integer userId) {
        Example example = new Example(UserCheckCount.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<UserCheckCount> userCheckCounts = userCheckCountMapper.selectByExample(example);
        return userCheckCounts.isEmpty() ? null : userCheckCounts.get(0);
    }
}
