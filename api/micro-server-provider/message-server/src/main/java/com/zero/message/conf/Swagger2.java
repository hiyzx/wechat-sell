package com.zero.message.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder timestampParam = new ParameterBuilder();
        timestampParam.name("timestamp").description("timestamp").modelRef(new ModelRef("string"))
            .parameterType("header").required(true).build();
        ParameterBuilder authorizationParam = new ParameterBuilder();
        authorizationParam.name("authorization").description("authorization").modelRef(new ModelRef("string"))
            .parameterType("header").required(true).build();
        parameters.add(timestampParam.build());
        parameters.add(authorizationParam.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.zero.message.web.controller")).paths(PathSelectors.any())
            .build().globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("外卖系统").description("消息服务").license("仅供内部参考")
            .contact(new Contact("zero", "https://github.com/hiyzx/takeaway", "452002276@qq.com"))
            .termsOfServiceUrl(null).build();
    }
}