package com.zero.admin.bean;

import com.zero.admin.util.HttpClient;
import com.zero.admin.vo.http.properties.HttpJuHeProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Configuration
@Data
public class HttpClientBean {

    @Resource
    private HttpJuHeProperties httpJuHeProperties;

    @Bean("juHeHttpClient")
    public HttpClient juHeHttpClient() {
        return new HttpClient(httpJuHeProperties.getScheme(), httpJuHeProperties.getHostname(),
                Integer.valueOf(httpJuHeProperties.getPort()));
    }
}
