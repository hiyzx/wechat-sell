package com.zero.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.zero.*.feign")
@EnableTransactionManagement
@EnableDistributedTransaction
@EnableCircuitBreaker
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
