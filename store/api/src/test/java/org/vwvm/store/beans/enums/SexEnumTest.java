package org.vwvm.store.beans.enums;

import org.vwvm.ApiApplication;
import org.vwvm.store.beans.entity.User;
import org.vwvm.store.mappers.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;


@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class SexEnumTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void sexEnumTest(){

        User user = new User();
        user.setUserName("sex");
        user.setPassword("sex");
        user.setSex(SexEnum.MALE.getSex());
        int result = userMapper.insert(user);
        System.out.println(result);
    }
}