package com.zero.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.zero.*.feign")
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
