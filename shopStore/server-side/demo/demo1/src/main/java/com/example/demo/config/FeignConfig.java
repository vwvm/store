package com.example.demo.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h3>store</h3>
 * <p>feign配置文件</p>
 *
 * @author : BlackBox
 * @date : 2023-03-21 22:29
 **/
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLevelConfig(){
        return Logger.Level.FULL;
    }


}
