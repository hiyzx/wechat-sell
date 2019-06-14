package com.zero.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.UserPoint;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserPointService extends IService<UserPoint> {

    void add(Integer userId);
}
