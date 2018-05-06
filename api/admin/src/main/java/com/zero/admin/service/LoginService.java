package com.zero.admin.service;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.product.dao.StoreMapper;
import com.zero.product.po.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@Service
@Slf4j
public class LoginService {

    @Resource
    private StoreMapper storeMapper;

    public Store login(String name) throws BaseException {
        Condition condition = new Condition(Store.class);
        condition.createCriteria().andEqualTo("name", name);
        List<Store> users = storeMapper.selectByExample(condition);
        if (users.isEmpty()) {
            throw new BaseException(CodeEnum.LOGIN_FALL, "登录失败!");
        } else {
            return users.get(0);
        }
    }
}
