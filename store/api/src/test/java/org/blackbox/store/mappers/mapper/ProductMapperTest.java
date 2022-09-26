package org.blackbox.store.mappers.mapper;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.bean.ProductBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
class ProductMapperTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    void testRecommend(){
        List<ProductBean> productBeans = productMapper.selectRecommendProducts();
        for (ProductBean productBean:productBeans){
            System.out.println(productBean);
        }
    }

    @Test
    void testSelectBy(){
        productMapper.selectByMap(Map.of("product_id","1","product_status",1));

    }

}