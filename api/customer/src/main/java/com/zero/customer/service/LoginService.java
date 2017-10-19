package com.zero.customer.service;

import com.zero.common.constants.PointConstant;
import com.zero.common.dao.UserMapper;
import com.zero.common.enums.CodeEnum;
import com.zero.common.enums.PointTypeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.po.User;
import com.zero.common.util.DateHelper;
import com.zero.customer.vo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@Service
@Slf4j
public class LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserPointService userPointService;
    @Resource
    private UserService userService;

    public User register(UserDto userDto) throws BaseException {
        String phone = userDto.getPhone();
        User tmp = new User();
        tmp.setAge(userDto.getAge());
        String name = userDto.getName();
        tmp.setName(name);
        tmp.setPhone(phone);
        tmp.setPassword(userDto.getPassword());
        tmp.setLastLoginTime(DateHelper.getCurrentDateTime());
        userMapper.insertSelective(tmp);
        int userId = tmp.getId();// 只能用这种方式获取id
        log.info("userId={} name={} phone={} register success", userId, name, phone);
        userPointService.add(userId);
        return tmp;
    }

    public User login(String phone, String password) throws BaseException {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("phone", phone).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(condition);
        if (users.isEmpty()) {
            throw new BaseException(CodeEnum.LOGIN_FALL, "帐号或者密码错误!");
        } else {
            User user = users.get(0);
            Date lastLoginTime = user.getLastLoginTime();
            Integer userId = user.getId();
            Date now = DateHelper.getCurrentDateTime();
            if (lastLoginTime == null || !DateHelper.isSameDate(now, lastLoginTime)) {
                userPointService.increasePoint(userId, PointTypeEnum.登录, PointConstant.POINT_LOGIN);
            }
            User tmp = new User();
            tmp.setId(userId);
            tmp.setLastLoginTime(now);
            userMapper.updateByPrimaryKeySelective(tmp);
            log.info("userId={} login success", userId);
            return user;
        }
    }

    public void restPassword(String phone, String password1, String password2) throws BaseException {
        if (password1 == null || !password1.equals(password2)) {
            throw new BaseException(CodeEnum.PASSWORD_NOT_CONSISTENT, "密码不一致!");
        }
        User user = userService.getUserByPhone(phone);
        User tmp = new User();
        int userId = user.getId();
        tmp.setId(userId);
        tmp.setPassword(password1);
        userMapper.updateByPrimaryKeySelective(tmp);
        log.info("userId={} rest password", userId);
    }
}
