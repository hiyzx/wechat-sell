package com.zero.customer.service;

import com.zero.common.constants.PointConstant;
import com.zero.common.exception.BaseException;
import com.zero.common.util.DateHelper;
import com.zero.common.util.NumberUtil;
import com.zero.customer.enums.CustomerCodeEnum;
import com.zero.customer.vo.CheckRecordVo;
import com.zero.user.dao.UserCheckCountMapper;
import com.zero.user.dao.UserMapper;
import com.zero.user.po.User;
import com.zero.user.po.UserCheckCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Resource
    private UserCheckCountService userCheckCountService;
    @Resource
    private UserCheckCountMapper userCheckCountMapper;
    @Resource
    private UserPointService userPointService;
    @Resource
    private UserMapper userMapper;

    public boolean existPhone(String phone) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("phone", phone);
        return userMapper.selectCountByExample(condition) > 0;
    }

    public User getUserByPhone(String phone) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("phone", phone);
        List<User> users = userMapper.selectByExample(condition);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public void check(Integer userId) throws BaseException {
        UserCheckCount userCheckCount = userCheckCountService.getByUserId(userId);
        Date now = DateHelper.getCurrentDateTime();
        // 第一次签到
        if (userCheckCount == null) {
            UserCheckCount tmp = new UserCheckCount();
            tmp.setUserId(userId);
            tmp.setCheckTime(now);
            tmp.setContinueCount(1);
            tmp.setMaxCount(1);
            tmp.setSum(1);
            tmp.setHistory(NumberUtil.moveByte(0, 1));
            userCheckCountMapper.insertSelective(tmp);
            log.info("userId={} first time check", userId);
            // 签到增加积分
            userPointService.increasePoint(userId, PointConstant.POINT_TYPE_CHECK, PointConstant.POINT_CHECK);
        } else {// 非第一次签到
            Date checkTime = userCheckCount.getCheckTime();
            boolean sameDate = DateHelper.isSameDate(checkTime, now);
            // 同一天签到不加分
            if (!sameDate) {
                int daysBetween = DateHelper.daysBetween(checkTime, now);
                UserCheckCount tmp = new UserCheckCount();
                tmp.setId(userCheckCount.getId());
                tmp.setCheckTime(now);
                tmp.setSum(userCheckCount.getSum() + 1);
                tmp.setHistory(NumberUtil.moveByte(userCheckCount.getHistory(), daysBetween));
                if (daysBetween > 1) {
                    tmp.setContinueCount(1);
                } else {
                    int continueCount = userCheckCount.getContinueCount() + 1;
                    tmp.setContinueCount(continueCount);
                    if (continueCount > userCheckCount.getMaxCount()) {
                        tmp.setMaxCount(continueCount);
                    }
                    Integer point = continueCheckScore(continueCount);
                    if (point != null) {
                        userPointService.increasePoint(userId, PointConstant.POINT_TYPE_CONTINUE_CHECK, point);
                    }
                }
                userCheckCountMapper.updateByPrimaryKeySelective(tmp);
                log.info("userId={} continue check day={}", userId, tmp.getContinueCount());
                // 签到增加积分
                userPointService.increasePoint(userId, PointConstant.POINT_TYPE_CHECK, PointConstant.POINT_CHECK);
            } else {
                throw new BaseException(CustomerCodeEnum.CHECK_REPEAT, "今天已经签到过了!");
            }
        }
    }

    /**
     * 连续签到获取积分规则
     */
    private Integer continueCheckScore(int continueCheckCount) {
        Integer rtn;
        if (continueCheckCount == PointConstant.CONTINUE_CHECK_DAY_3) {
            rtn = PointConstant.POINT_CONTINUE_3;
        } else if (continueCheckCount == PointConstant.CONTINUE_CHECK_DAY_7) {
            rtn = PointConstant.POINT_CONTINUE_7;
        } else if (continueCheckCount == PointConstant.CONTINUE_CHECK_DAY_30) {
            rtn = PointConstant.POINT_CONTINUE_30;
        } else {
            rtn = null;
        }
        return rtn;
    }

    public CheckRecordVo queryCheckRecord(Integer userId) {
        UserCheckCount userCheckCount = userCheckCountService.getByUserId(userId);
        CheckRecordVo rtn = new CheckRecordVo();
        if (userCheckCount == null) {
            rtn.setHasCheck(false);
        } else {
            Date now = DateHelper.getCurrentDateTime();
            rtn.setHasCheck(DateHelper.isSameDate(now, userCheckCount.getCheckTime()));
            rtn.setLastCheckTime(userCheckCount.getCheckTime());
            String checkHistory = NumberUtil.toFullBinaryString(userCheckCount.getHistory());
            rtn.setCheckHistory(checkHistory.substring(checkHistory.indexOf("1")));
        }
        return rtn;
    }
}
