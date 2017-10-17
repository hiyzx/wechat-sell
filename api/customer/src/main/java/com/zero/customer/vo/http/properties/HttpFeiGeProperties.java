package com.zero.customer.vo.http.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description 需要在pom.xml中加入spring-boot-configuration-processor依赖
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Component
@ConfigurationProperties(prefix = "http.feige")
@Data
public class HttpFeiGeProperties {

    private String scheme;// 协议
    private String hostname;// 域名
    private String port;// 端口

}
