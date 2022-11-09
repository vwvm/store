package org.vwvm.store.api.config;

import io.swagger.v3.oas.annotations.servers.Server;
import org.vwvm.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//
@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
@Server
public class MainTest {


    @Test
    void async() throws InterruptedException {

    }


}
