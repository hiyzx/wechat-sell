package com.zero.user.service;

import com.zero.user.common.BaseService;
import com.zero.user.po.UserPoint;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserPointService extends BaseService<UserPoint> {

    void add(Integer userId);
}
