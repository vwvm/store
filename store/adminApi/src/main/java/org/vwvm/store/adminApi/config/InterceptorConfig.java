package org.vwvm.store.adminApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.vwvm.store.adminApi.interceptor.TokenInterceptor;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("")
                .excludePathPatterns(
                        List.of("/users/**",
                                "/swagger-ui/**",
                                "/doc.html/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/v3/**"
                                ,"/test/**"
                                ,"/index/**",
                                "/product/**"
                        )
                );
    }
}
