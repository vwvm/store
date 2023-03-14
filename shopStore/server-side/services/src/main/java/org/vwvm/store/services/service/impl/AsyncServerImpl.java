package org.vwvm.store.services.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class AsyncServerImpl {

    Random random = new Random();

    @Async()
    public Future<String> doTaskOne() throws InterruptedException {
        System.out.println("开始任务一");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务一，耗时 %d\n" ,end - start);
        return new AsyncResult<>("第一任务完成");
    }
    @Async()
    public Future<String> doTaskTwo() throws InterruptedException {
        System.out.println("开始任务二");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务二，耗时 %d \n" ,end - start);
        return new AsyncResult<>("第二任务完成");
    }
    @Async()
    public Future<String> doTaskThree() throws InterruptedException {
        System.out.println("开始任务三");
        Long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long end = System.currentTimeMillis();
        System.out.printf("完成任务三，耗时 %d \n" ,end - start);
        return new AsyncResult<>("第三任务完成");
    }
}
