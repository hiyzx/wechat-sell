package com.zero.user.service.impl;

import com.zero.user.dao.UserPointMapper;
import com.zero.user.po.UserPoint;
import com.zero.user.service.UserPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
@Slf4j
public class UserPointServiceImpl extends AbstractService<UserPoint> implements UserPointService {

    @Resource
    private UserPointMapper userPointMapper;

    public void add(Integer userId) {
        UserPoint userPoint = new UserPoint();
        userPoint.setUserId(userId);
        userPoint.setPoint(0);
        userPointMapper.insert(userPoint);
        log.info("userId={} add userPoint object", userId);
    }
}
