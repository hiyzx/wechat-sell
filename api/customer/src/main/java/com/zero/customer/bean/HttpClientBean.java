package com.zero.customer.bean;

import com.zero.customer.util.HttpClient;
import com.zero.customer.vo.http.properties.HttpFeiGeProperties;
import com.zero.customer.vo.http.properties.HttpRemoteProperties;
import com.zero.customer.vo.http.properties.HttpWeChatProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * http bean对象
 *
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Configuration
@Data
public class HttpClientBean {

    @Resource
    private HttpFeiGeProperties httpFeiGeProperties;
    @Resource
    private HttpWeChatProperties httpWeChatProperties;
    @Resource
    private HttpRemoteProperties httpRemoteProperties;

    @Bean("feiGeHttpClient")
    public HttpClient feiGeHttpClient() {
        return new HttpClient(httpFeiGeProperties.getScheme(), httpFeiGeProperties.getHostname(),
                Integer.valueOf(httpFeiGeProperties.getPort()));
    }

    @Bean("weChatHttpClient")
    public HttpClient weChatHttpClient() {
        return new HttpClient(httpWeChatProperties.getScheme(), httpWeChatProperties.getHostname(),
                Integer.valueOf(httpWeChatProperties.getPort()));
    }

    @Bean("remoteHttpClient")
    public HttpClient remoteHttpClient() {
        return new HttpClient(httpRemoteProperties.getScheme(), httpRemoteProperties.getHostname(),
                Integer.valueOf(httpRemoteProperties.getPort()));
    }
}
