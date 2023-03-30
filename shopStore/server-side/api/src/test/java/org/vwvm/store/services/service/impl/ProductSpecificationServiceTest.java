package org.vwvm.store.services.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.entity.ProductSpecification;

import java.util.List;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class ProductSpecificationServiceTest {

    @Resource
    private ProductSpecificationServiceImpl productSpecificationService;

    @Test
    void productSpecificationServiceList() {
        List<ProductSpecification> list = productSpecificationService.list();
        list.get(1).getUntitled().forEach((x, y)->{
            System.out.println(x);
            System.out.println(y);
        });
    }


}