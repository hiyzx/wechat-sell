package com.zero.message.bean;

import com.zero.common.util.HttpClient;
import com.zero.message.vo.http.properties.HttpFeiGeProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Configuration
@Data
public class HttpClientBean {

    @Resource
    private HttpFeiGeProperties httpFeiGeProperties;

    @Bean("feiGeHttpClient")
    public HttpClient feiGeHttpClient() {
        return new HttpClient(httpFeiGeProperties.getScheme(), httpFeiGeProperties.getHostname(),
                Integer.valueOf(httpFeiGeProperties.getPort()));
    }
}
