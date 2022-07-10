package org.blackbox.store.mappers.dao;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class UsersDaoTest {

    @Resource
    private UsersDao usersDao;

    @Test
    void queryUserByName() {
        Users users = usersDao.queryUserByName("aaaa");
        System.out.println(users);
    }
}