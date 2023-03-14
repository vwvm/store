package org.vwvm.store.services.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.bean.ProductCategoryBean;
import org.vwvm.store.mappers.mapper.ProductCategoryMapper;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class ProductCategoryServiceImplTest {


    @Resource
    ProductCategoryMapper productCategoryMapper;

    @Resource
    ProductCategoryServiceImpl productCategoryService;

    @Test
    void productCategory() {
        System.out.println(productCategoryService.productCategory());
    }

    @Test
    void testProductCategory() {
        List<ProductCategoryBean> productCategoryBeans = productCategoryService.productCategory();
        productCategoryBeans.forEach(System.out::println);
    }
}