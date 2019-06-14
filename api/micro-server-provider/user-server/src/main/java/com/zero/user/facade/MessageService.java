package com.zero.user.facade;

import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.user.util.RedisHelper;
import com.zero.common.util.StringHelper;
import com.zero.message.dto.req.SendMsgRequest;
import com.zero.message.feign.MessageServerClient;
import com.zero.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.zero.common.constants.SystemConstants.MESSAGE_TYPE_FORGET;
import static com.zero.common.constants.SystemConstants.MESSAGE_TYPE_REGISTER;

/**
 * @author yezhaoxing
 * @date 2017/09/26
 */
@Service
@Slf4j
public class MessageService {

    private static final int MSG_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(5)).intValue();
    private static final int MSG_OFTEN_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(1)).intValue();
    @Resource
    private RedisHelper<String, String> redisHelper;
    @Autowired
    private UserService userService;
    @Resource
    private MessageServerClient messageServerClient;

    public void sendMsg(String phone, Integer type) throws BaseException, IOException {
        String wrapperOftenKey = wrapperOftenKey(phone, type);
        if (redisHelper.get(wrapperOftenKey) == null) {
            boolean existPhone = userService.existPhone(phone);
            if (type == MESSAGE_TYPE_REGISTER && existPhone) {
                throw new BaseException(CodeEnum.PHONE_HAS_EXIST, "手机号已经存在!");
            }
            if (type == MESSAGE_TYPE_FORGET && !existPhone) {
                throw new BaseException(CodeEnum.PHONE_NOT_EXIST, "该手机号还未注册!");
            }
            String code = StringHelper.generateCode();
            // 调用短信接口发送短信
            messageServerClient
                    .sendMsgAlone(new SendMsgRequest(284, "notice", "验证码", String.format("您的短信验证码为%s", code), ""));

            log.info("send message to phone={} type={} code={}", phone, type, code);
            redisHelper.set(wrapperMsgKey(phone, type), code, MSG_EXPIRED_SECONDS);
            redisHelper.set(wrapperOftenKey, "1", MSG_OFTEN_EXPIRED_SECONDS);
        } else {
            throw new BaseException(CodeEnum.OFTEN_SEND_MSG, "短信发送过于频繁");
        }
    }

    public void validMsg(String phone, Integer type, String inputCode) throws BaseException {
        String key = wrapperMsgKey(phone, type);
        String redisCode = redisHelper.get(key);
        if (redisCode == null) {
            throw new BaseException(CodeEnum.CODE_HAS_EXPIRE, "验证码已过期!");
        } else {
            if (!redisCode.equals(inputCode)) {
                throw new BaseException(CodeEnum.CODE_IS_WRONG, "验证码错误!");
            } else {
                redisHelper.delete(key);
            }
        }
    }

    private String wrapperMsgKey(String phone, Integer type) {
        return String.format("msg-%s-%S", phone, type);
    }

    private String wrapperOftenKey(String phone, Integer type) {
        return String.format("often-%s-%S", phone, type);
    }
}
