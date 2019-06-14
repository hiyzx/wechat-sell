package com.zero.user.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.UserPointRecord;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserPointRecordService extends IService<UserPointRecord> {

    void increasePoint(Integer userId, Integer type, Integer score);
}
