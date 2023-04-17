package org.vwvm.paperManagement.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;
import org.vwvm.paperManagement.entity.User;
import org.vwvm.paperManagement.service.impl.UserServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class IUserServiceTest {

    @Resource
    UserServiceImpl userService;

    @Test
    void getUserList(){
        List<User> list = userService.list();
        list.forEach(user -> {
            System.out.println(user.getUser_roles().get(0));
        });
    }
}