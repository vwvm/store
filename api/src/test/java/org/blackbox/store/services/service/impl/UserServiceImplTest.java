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
    void insertBatchTest() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setUserName("" + i);
            user.setPassword("" + i + i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}