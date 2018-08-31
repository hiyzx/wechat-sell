package com.zero.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zero.user.dao")
@ComponentScan(basePackages = "com.zero")
@EnableDubboConfiguration
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
