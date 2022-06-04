package org.blackbox.store.mappers.dao;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    void queryUserByName() {
        User user = userDao.queryUserByName("xx");
        System.out.println(user);
    }
}