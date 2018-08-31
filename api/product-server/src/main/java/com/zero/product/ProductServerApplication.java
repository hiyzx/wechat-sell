package com.zero.product;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zero.product.dao")
@ComponentScan(basePackages = "com.zero.product")
@EnableDubboConfiguration
public class ProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }
}
