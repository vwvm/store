package org.vwvm.store.adminApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//swagger3注解
@EnableOpenApi
public class SwaggerConfig {

    //swagger3
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("BlackBox")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.vwvm.store.adminApi.controller"))
//      .paths(PathSelectors.ant("/controller/**"))
                .build();
    }


    @SuppressWarnings("all")
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "store's adminApi",
                "store project",
                "v1.0",
                "1647010108@qq.com",
                "BlockBox",
                "Apache 2.0",  //许可证
                "http://www.apache.org/licenses/LICENSE-2.0" //许可证链接
        );
    }
}
