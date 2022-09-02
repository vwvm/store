package org.blackbox.store.api.config;

import org.blackbox.ApiApplication;
import org.blackbox.store.services.service.impl.AsyncServerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.concurrent.Future;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class AsyncConfigTest {

    private  final Logger log =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    AsyncServerImpl mainTest = new AsyncServerImpl();

    @Test
    void async() throws InterruptedException {

        Long start = System.currentTimeMillis();

        Future<String> one = mainTest.doTaskOne();
        Future<String> two = mainTest.doTaskTwo();
        Future<String> three = mainTest.doTaskThree();



        while (!(one.isDone() && two.isDone() && three.isDone())){
            Thread.sleep(500);
        }
        Long end = System.currentTimeMillis();
        System.out.printf("总耗时：%d \n", end - start);
    }
}