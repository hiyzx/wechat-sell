package com.zero.product.feign;

import org.springframework.cloud.openfeign.support.FallbackCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServerClientFallback extends FallbackCommand<ProductServerClient> {


    public ProductServerClientFallback(ProductServerClient result, String groupname) {
        super(result, HystrixCommandGroupKey.Factory.asKey(groupname));

    }

}