package com.zero.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yezhaoxing
 * @date : 2017/8/17
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zero.*.dao")
@ComponentScan(basePackages = "com.zero")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
