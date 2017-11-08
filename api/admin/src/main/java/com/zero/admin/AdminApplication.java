package com.zero.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zero.common.dao")
@ComponentScan(basePackages = "com.zero")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
