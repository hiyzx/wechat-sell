package com.zero.customer.service;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.util.StringHelper;
import com.zero.customer.util.RedisHelper;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
public class MessageService {

    private static final int MSG_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(5)).intValue();
    private static final int MSG_OFTEN_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(5)).intValue();
    @Resource
    private RedisHelper<String, String> redisHelper;

    public void sendMsg(String phone, Integer type) throws BaseException {
        String wrapperOftenKey = wrapperOftenKey(phone, type);
        if (redisHelper.get(wrapperOftenKey) == null) {
            String code = StringHelper.generateCode();
            // 调用短信接口发送短信
            redisHelper.set(wrapperMsgKey(phone, type), code, MSG_EXPIRED_SECONDS);
            redisHelper.set(wrapperOftenKey, "1", MSG_OFTEN_EXPIRED_SECONDS);
        } else {
            throw new BaseException(CodeEnum.OFTEN_SEND_MSG, "短信发送过于频繁");
        }
    }

    private String wrapperMsgKey(String phone, Integer type) {
        return String.format("msg-%s-%S", phone, type);
    }

    private String wrapperOftenKey(String phone, Integer type) {
        return String.format("often-%s-%S", phone, type);
    }
}
