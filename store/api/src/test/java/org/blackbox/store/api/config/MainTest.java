package org.blackbox.store.api.config;

import io.swagger.v3.oas.annotations.servers.Server;
import org.blackbox.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Future;

//
@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
@Server
public class MainTest {


    @Test
    void async() throws InterruptedException {

    }


}
