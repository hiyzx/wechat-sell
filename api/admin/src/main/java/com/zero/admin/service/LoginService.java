package com.zero.admin.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.product.po.Store;
import com.zero.product.service.StoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@Service
@Slf4j
public class LoginService {

    @Reference
    private StoreService storeService;

    public Store login(String storeName) throws BaseException {
        Store store = storeService.findByName(storeName);
        if (store == null) {
            throw new BaseException(CodeEnum.LOGIN_FALL, "登录失败!");
        }
        return store;
    }
}
