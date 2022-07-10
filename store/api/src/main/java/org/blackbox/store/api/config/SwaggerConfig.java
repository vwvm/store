package org.blackbox.store.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // swagger 会帮助我们生成接口文档
    @Bean
    public Docket getDocket(){

        //生成封面信息
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("商城后端接口说明")
                .description("本文档详细说明了项目后端的接口规范")
                .version("1.0.0")
                .contact(new Contact("blackbox", "", "1647010108@qq.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();
        //指定文档风格
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //生成策略
        docket.apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.blackbox.store.api.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
}
