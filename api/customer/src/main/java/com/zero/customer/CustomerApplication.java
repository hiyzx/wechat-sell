package com.zero.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * @author yezhaoxing
 * @date : 2017/8/17
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zero")
@EnableDubboConfiguration
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
