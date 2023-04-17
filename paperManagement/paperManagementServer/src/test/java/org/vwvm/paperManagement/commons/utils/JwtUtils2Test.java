package org.vwvm.paperManagement.commons.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtils2Test {

    @Test
    void TestJwt2() throws InterruptedException {
        String key = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();
        String key3 = UUID.randomUUID().toString();
        System.out.println(key);
        System.out.println(key2);
        System.out.println(key3);

        String jwt = JwtUtils2.createJwt(new Date(System.currentTimeMillis() + 1000 * 1 ), Map.of("数据1", "数据1"));
        System.out.println( "jwt: " + jwt);
        Thread.sleep(1 * 1000);
        int i = JwtUtils2.parseJwt(jwt);
        System.out.println(i);
        Map<String, Object> data = JwtUtils2.getPayLoad(jwt);
        System.out.println(data);
    }
}