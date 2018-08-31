package com.zero.user.service.impl;

import com.zero.common.util.DateHelper;
import com.zero.user.dao.UserPointRecordMapper;
import com.zero.user.po.UserPointRecord;
import com.zero.user.service.UserPointRecordService;
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
public class UserPointRecordServiceImpl extends AbstractService<UserPointRecord> implements UserPointRecordService {

    @Resource
    private UserPointRecordMapper userPointRecordMapper;

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
}
