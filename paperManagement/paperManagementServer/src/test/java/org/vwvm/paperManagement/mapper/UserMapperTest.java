package org.vwvm.paperManagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;
import org.vwvm.paperManagement.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class UserMapperTest {

    @Resource
    UserMapper userMapper;


    @Test
    void getUserList(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_username", "admin");
        userQueryWrapper.last("limit 1");
        User user = userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
    }
}