package org.vwvm.store.mappers.listMapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.bean.ProductCategoryBean;


import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class ProductCategoryBeanMapperTest {

    @Resource
    ProductCategoryBeanMapper productCategoryBeanMapper;

    @Test
    void selectProductCategory() {
        List<ProductCategoryBean> productCategoryBeans = productCategoryBeanMapper.selectProductCategory();
        productCategoryBeans.forEach(System.out::println);
    }
}