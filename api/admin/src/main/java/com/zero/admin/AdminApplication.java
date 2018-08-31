package com.zero.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zero")
@EnableDubboConfiguration
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
