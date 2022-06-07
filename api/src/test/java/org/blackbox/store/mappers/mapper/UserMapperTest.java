package org.blackbox.store.mappers.mapper;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void queryUserByName() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void insertTest(){
        User user = new User();
        user.setUserImg("123.i");
        user.setUserName("hah");
        user.setPassword("123");
        int result = userMapper.insert(user);
        System.out.println(result);
    }
}