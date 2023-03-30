package org.vwvm.store.paperManagement.paperManagementServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.LinkedList;

@SpringBootApplication
@MapperScan("org.vwvm.store.paperManagement.paperManagementServer.mapper")
@EnableFeignClients
public class PaperManagementServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PaperManagementServerApplication.class, args);
        System.out.println(run);
    }

}
