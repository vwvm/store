package org.vwvm.store.apiserver.commons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    @Value("${app.dir}")
    private String dir;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App Name: " + appName);
        System.out.println("App Description: " + appDescription);
        System.out.println("App dir" + dir);
    }
}
