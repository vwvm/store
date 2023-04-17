package org.vwvm.paperManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

// @EnableFeignClients
public class PaperApplication {

	public static void main(String[] args) throws Throwable {
		ConfigurableApplicationContext run = SpringApplication.run(PaperApplication.class, args);

	}

}
