package com.zero.customer.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Component
@Slf4j
public class ScheduleTask {

    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshAccessToken() {
        log.info("refresh success");
    }
}
