package com.zero.customer.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;

/**
 * @author yezhaoxing
 * @date 2017/11/20
 */
@Configuration
public class DocumentBean {

    private static final String ACCESS_KEY_ID = "7914f998dbc54f548300d3db442a1f5a";
    private static final String SECRET_ACCESS_KEY = "68837af26d2144e38109059bbdf8006c";

    @Bean
    public DocClient docClient() {
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        return new DocClient(config);
    }
}
