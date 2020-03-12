package com.zero.order.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zero.product.feign.ProductServerClientFallbackFactory;

/**
 * @author yezhaoxing
 * @date 2019/6/16
 */
@Configuration
public class FeignFallbackBean {

	@Bean
	public ProductServerClientFallbackFactory productServerClientFallbackFactory(){
		return new ProductServerClientFallbackFactory();
	}
}
