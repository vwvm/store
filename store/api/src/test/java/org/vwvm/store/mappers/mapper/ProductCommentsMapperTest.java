package org.vwvm.store.mappers.mapper;

import org.vwvm.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class ProductCommentsMapperTest {

    @Resource
    private ProductCommentsMapper productCommentsMapper;

    @Test
    void selectProductCommentsById() {
        System.out.println(productCommentsMapper.selectProductCommentsById("3"));
    }
}