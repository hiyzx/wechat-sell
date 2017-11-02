package com.zero.customer.schedule;

import com.zero.customer.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Component
@Slf4j
public class ScheduleTask {

    @Resource
    private WeChatService weChatService;

    @Scheduled(fixedRate = 1000 * 60 * 90)
    public void refreshAccessToken() {
        weChatService.refreshAccessToken();
        log.info("refresh access token success");
    }
}
