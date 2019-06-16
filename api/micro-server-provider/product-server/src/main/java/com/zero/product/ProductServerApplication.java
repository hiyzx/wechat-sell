package com.zero.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

/**
 * @author yezhaoxing
 * @since 2018/08/30
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableDistributedTransaction
public class ProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }
}
