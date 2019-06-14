package com.zero.user.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.UserCheckCount;

/**
 * @author zero
 * @since 2018/08/30
 */
public interface UserCheckCountService extends IService<UserCheckCount> {

    UserCheckCount getByUserId(Integer userId);
}
