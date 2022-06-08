package org.blackbox.store.api.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.User;
import org.blackbox.store.mappers.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class MybatisPlusConfigTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void pageTest(){
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());

    }
}