package org.vwvm.store.adminApi.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
 
 
@Configuration
public class UsingStaticController extends WebMvcConfigurationSupport {
 
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // classpath表示在resource目录下，/static/** 表示在URL路径中访问如
        // http://localhost:8080/static/ 即可访问到resource下的static目录
      registry.addResourceHandler("/static/**").addResourceLocations("classpath:/templates/static/");
      registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/templates/lib/");
    }
}