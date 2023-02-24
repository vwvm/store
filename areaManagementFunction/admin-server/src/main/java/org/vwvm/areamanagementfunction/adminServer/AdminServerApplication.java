package org.vwvm.areamanagementfunction.adminServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("org.vwvm.areamanagementfunction.adminServer.mapper")
public class AdminServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AdminServerApplication.class, args);
        System.out.println(run);
    }

}
