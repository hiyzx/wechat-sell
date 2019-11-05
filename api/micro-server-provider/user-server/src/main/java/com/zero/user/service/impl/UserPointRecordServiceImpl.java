package com.zero.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.UserPointRecord;
import com.zero.common.util.DateHelper;
import com.zero.user.dao.UserPointRecordMapper;
import com.zero.user.service.UserPointRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @since 2018/08/30
 */
@Service
@Slf4j
public class UserPointRecordServiceImpl extends ServiceImpl<UserPointRecordMapper, UserPointRecord>
        implements UserPointRecordService {

    @Override
    public void increasePoint(Integer userId, Integer type, Integer score) {
        // 减少并发问题
        // userPointExtMapper.increasePoint(userId, score);
        UserPointRecord userPointRecord = new UserPointRecord();
        userPointRecord.setUserId(userId);
        userPointRecord.setType(type);
        userPointRecord.setGainPoint(score);
        userPointRecord.setCreateTime(DateHelper.getCurrentDateTime());
        this.insert(userPointRecord);
        log.info("userId={} increase score={} from type{}", userId, score, type);
    }
}
