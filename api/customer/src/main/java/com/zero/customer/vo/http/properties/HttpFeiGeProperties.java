package com.zero.customer.vo.http.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("http请求属性对象")
public class HttpFeiGeProperties {

    @ApiModelProperty("协议")
    private String scheme;

    @ApiModelProperty("域名")
    private String hostname;

    @ApiModelProperty("端口")
    private String port;// 端口

}
