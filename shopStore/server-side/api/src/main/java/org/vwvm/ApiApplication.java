package org.vwvm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = {"org.vwvm.store.mappers.dao", "org.vwvm.store.mappers.mapper","org.vwvm.store.mappers.adminMapper", "org.vwvm.store.mappers.listMapper"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
