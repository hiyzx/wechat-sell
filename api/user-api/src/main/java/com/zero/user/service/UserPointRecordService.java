package com.zero.user.service;

import com.zero.user.common.BaseService;
import com.zero.user.po.UserPointRecord;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserPointRecordService extends BaseService<UserPointRecord> {

    void increasePoint(Integer userId, Integer type, Integer score);
}
