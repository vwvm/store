package org.blackbox.store.mappers.mapper;


import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class CategoryMapperTest {

    @Resource
    private CategoryMapper categoryMapper;


    @Test
    void queryUserByName() {
        System.out.println(("----- selectAll method test ------"));
        List<Category> userList = categoryMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void insertCategory(){

        Category category = new Category(1001, "测试", 1, 0, "01.png", "hehe", "aaa.jpg", "dd");

        int i = categoryMapper.insert(category);
        System.out.println(i);
    }


}