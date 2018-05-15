package com.zero.customer.service;

import com.zero.common.util.DateHelper;
import com.zero.user.dao.UserPointMapper;
import com.zero.user.dao.UserPointRecordMapper;
import com.zero.user.dao.ext.UserPointExtMapper;
import com.zero.user.po.UserPoint;
import com.zero.user.po.UserPointRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zero
 * @date 2017/08/17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserPointService {

    @Resource
    private UserPointRecordMapper userPointRecordMapper;
    @Resource
    private UserPointExtMapper userPointExtMapper;
    @Resource
    private UserPointMapper userPointMapper;

    public void increasePoint(Integer userId, Integer type, Integer score) {
        // 减少并发问题
        // userPointExtMapper.increasePoint(userId, score);
        UserPointRecord userPointRecord = new UserPointRecord();
        userPointRecord.setUserId(userId);
        userPointRecord.setType(type);
        userPointRecord.setGainPoint(score);
        userPointRecord.setCreateTime(DateHelper.getCurrentDateTime());
        userPointRecordMapper.insertSelective(userPointRecord);
        log.info("userId={} increase score={} from type{}", userId, score, type);
    }

    public void add(Integer userId) {
        UserPoint userPoint = new UserPoint();
        userPoint.setUserId(userId);
        userPoint.setPoint(0);
        userPointMapper.insert(userPoint);
        log.info("userId={} add userPoint object", userId);
    }
}
