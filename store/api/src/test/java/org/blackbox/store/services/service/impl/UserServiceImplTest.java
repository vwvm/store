package org.blackbox.store.services.service.impl;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.User;
import org.blackbox.store.services.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    void getCountTest() {
        long count = userService.count();
        System.out.println(count);
    }

    @Test
    void t1(StringBuilder s){
        String str = "abcde";
        StringBuilder stringBuilder = new StringBuilder(str);
        System.out.println(new StringBuilder(str).reverse());
    }

    @Test
    void t2(StringBuilder s){
        String str = "abcde";


    }

}