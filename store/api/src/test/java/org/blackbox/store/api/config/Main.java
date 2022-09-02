package org.blackbox.store.api.config;

import org.blackbox.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;
import java.util.concurrent.Future;

//
@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
public class Main {

    @Test
    void async() throws InterruptedException {
        Long start = System.currentTimeMillis();
        Future<String> one = doTaskOne();
        Future<String> two = doTaskTwo();
        Future<String> three = doTaskThree();

        Long end = System.currentTimeMillis();

        while (!(one.isDone() == two.isDone() == three.isDone())){
            Thread.sleep(500);
        }
        System.out.printf("总耗时：%d \n", end - start);
    }

    Random random = new Random();

    @Async("taskExecutor")
    Future<String> doTaskOne() throws InterruptedException {
        System.out.println("开始任务一");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务一，耗时 %d\n" ,end - start);
        return new AsyncResult<>("第一任务完成");
    }
    @Async("taskExecutor")
    Future<String> doTaskTwo() throws InterruptedException {
        System.out.println("开始任务二");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务二，耗时 %d \n" ,end - start);
        return new AsyncResult<>("第二任务完成");
    }
    @Async("taskExecutor")
    Future<String> doTaskThree() throws InterruptedException {
        System.out.println("开始任务三");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务三，耗时 %d \n" ,end - start);
        return new AsyncResult<>("第三任务完成");
    }
}
