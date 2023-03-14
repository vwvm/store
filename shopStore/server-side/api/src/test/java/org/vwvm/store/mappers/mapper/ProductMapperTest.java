package org.vwvm.store.mappers.mapper;

import org.vwvm.ApiApplication;
import org.vwvm.store.beans.bean.ProductBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.store.beans.entity.Product;

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
        List<Product> productBeans = productMapper.selectRecommendProducts();
        for (Product productBean:productBeans){
            System.out.println(productBean);
        }
    }

    @Test
    void testSelectBy(){
        productMapper.selectByMap(Map.of("product_id","1","product_status",1));
    }

    @Test
    void  selectCategoryProducts(){
        System.out.println(productMapper.selectCategoryProducts(1));
    }

}