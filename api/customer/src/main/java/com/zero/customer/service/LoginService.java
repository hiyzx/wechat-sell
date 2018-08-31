package com.zero.customer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.common.constants.PointConstant;
import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.util.DateHelper;
import com.zero.customer.vo.dto.UserDto;
import com.zero.user.po.User;
import com.zero.user.service.UserPointRecordService;
import com.zero.user.service.UserPointService;
import com.zero.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LoginService {

    @Reference
    private UserService userService;
    @Reference
    private UserPointService userPointService;
    @Reference
    private UserPointRecordService userPointRecordService;

    public User register(UserDto userDto) {
        String phone = userDto.getPhone();
        User tmp = new User();
        tmp.setAge(userDto.getAge());
        String name = userDto.getName();
        tmp.setName(name);
        tmp.setPhone(phone);
        tmp.setPassword(userDto.getPassword());
        tmp.setLastLoginTime(DateHelper.getCurrentDateTime());
        tmp.setIsDelete(false);
        userService.save(tmp);
        int userId = tmp.getId();// 只能用这种方式获取id
        log.info("userId={} name={} phone={} register success", userId, name, phone);
        userPointService.add(userId);
        return tmp;
    }

    public User login(String phone, String password) throws BaseException {
        User user = userService.queryByPhoneAndPassword(phone, password);
        if (user == null) {
            throw new BaseException(CodeEnum.LOGIN_FALL, "帐号或者密码错误!");
        } else {
            Date lastLoginTime = user.getLastLoginTime();
            Integer userId = user.getId();
            Date now = DateHelper.getCurrentDateTime();
            if (lastLoginTime == null || !DateHelper.isSameDate(now, lastLoginTime)) {
                userPointRecordService.increasePoint(userId, PointConstant.POINT_TYPE_LOGIN, PointConstant.POINT_LOGIN);
            }
            User tmp = new User();
            tmp.setId(userId);
            tmp.setLastLoginTime(now);
            userService.update(tmp);
            log.info("userId={} login success", userId);
            return user;
        }
    }

    public void restPassword(String phone, String password1, String password2) throws Exception {
        if (password1 == null || !password1.equals(password2)) {
            throw new BaseException(CodeEnum.PASSWORD_NOT_CONSISTENT, "密码不一致!");
        }
        User user = userService.findBy("phone", phone);
        User tmp = new User();
        int userId = user.getId();
        tmp.setId(userId);
        tmp.setPassword(password1);
        userService.update(tmp);
        log.info("userId={} rest password", userId);
    }
}
