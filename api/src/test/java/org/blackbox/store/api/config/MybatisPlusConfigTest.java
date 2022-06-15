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

    /**
     * 分页插件测试
     */
    @Test
    void pageTest() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    /**
     * 乐观锁测试
     */
    @Test
    void productTest() {
        User userA = userMapper.selectById(1);
        System.out.println("用户A获取年龄" + userA.getAge());
        User userB = userMapper.selectById(1);
        System.out.println("用户B获取年龄" + userB.getAge());
        //用户A操作年龄+20
        userA.setAge(userA.getAge() + 20);
        int updateByIdA = userMapper.updateById(userA);
        //用户A操作年龄-20
        userB.setAge(userB.getAge() - 20);
        int updateByIdB = userMapper.updateById(userB);
        System.out.println(updateByIdA);
        System.out.println(updateByIdB);

    }
}