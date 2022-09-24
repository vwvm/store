package org.blackbox.store.mappers.mapper;


import org.blackbox.ApiApplication;
import org.blackbox.store.beans.bean.CategoryBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectAllCategory() {

        List<CategoryBean> categoryBeans = categoryMapper.selectAllCategoryB(0);
        System.out.println("测试开始");
        for (CategoryBean categoryBean : categoryBeans) {
            System.out.println("\t" + categoryBean);
            for (CategoryBean categoryBean1 : categoryBean.getCategoryBeanList()) {
                System.out.println("\t\t" + categoryBean1);
                for (CategoryBean categoryBean2 : categoryBean1.getCategoryBeanList()) {
                    System.out.println("\t\t\t" + categoryBean2);
                }
            }
        }
    }

    @Test
    void selectFirstCategory(){
        List<CategoryBean> categoryBeans = categoryMapper.selectFirstLevelCategory();
        for (CategoryBean c:categoryBeans) {
            System.out.println(c);
        }

    }
}