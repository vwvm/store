package org.vwvm.paperManagement.commons.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class RedisUtilTest {

    @Autowired
    RedisUtil redisUtil;


    @Test
    void testRedis(){
        redisUtil.hset("a" , "b", "c");
        redisUtil.hset("a", "b1", "c1");
        Object hget = redisUtil.hget("a", "b1");
        Object hget1 = redisUtil.hget("a", "b");
        System.out.println(hget1);
        System.out.println(hget);

    }
}