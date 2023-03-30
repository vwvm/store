package org.vwvm.store.mappers.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        userList.forEach(System.out::println);
    }

    @Test
    void insertTest() {
        User user = new User();
        user.setUserImg("123.i");
        user.setUserName("hah");
        user.setPassword("123");
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    void deleteTest() {
        int i = userMapper.deleteById(8);
        System.out.println(i);
    }

    @Test
    void selectListWrapperTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "hah")
                .isNotNull("img");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    void wrapperSortTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void wrapperDeleteTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("age");
        System.out.println(userMapper.delete(queryWrapper));
    }

    @Test
    void wrapperUpdateTest() {
        //(img为空或者age>13)并且username带有a的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "13")
                .or(i -> i.gt("age", 5).isNull("img"));
        User user = new User();
        user.setUserImg("de.jpg");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    void lambdaQueryWrapperTest(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.select();
    }

    @Test
    void pageVoTest(){
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