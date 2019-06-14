package com.zero.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.UserPoint;
import com.zero.user.dao.UserPointMapper;
import com.zero.user.service.UserPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
@Slf4j
public class UserPointServiceImpl extends ServiceImpl<UserPointMapper, UserPoint> implements UserPointService {

    public void add(Integer userId) {
        UserPoint userPoint = new UserPoint();
        userPoint.setUserId(userId);
        userPoint.setPoint(0);
        this.insert(userPoint);
        log.info("userId={} add userPoint object", userId);
    }
}
