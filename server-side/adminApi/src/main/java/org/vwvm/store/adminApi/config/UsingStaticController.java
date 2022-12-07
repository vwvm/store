package org.vwvm.store.adminApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * WebMvcConfigurationSupport是webmvc的配置类，
 * 如果在springboot项目中，
 * 有配置类继承了WebMvcConfigurationSupport，
 * 那么webmvc的自动配置类WebMvcAutoConfiguration就会失效。
 */
@Configuration
public class UsingStaticController extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/templates/static/");
        registry.addResourceHandler("/lib/**")
                .addResourceLocations("classpath:/templates/lib/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}