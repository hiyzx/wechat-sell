package com.zero.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yezhaoxing
 * @date : 2017/8/17
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zero")
public class MessageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class, args);
    }

}
