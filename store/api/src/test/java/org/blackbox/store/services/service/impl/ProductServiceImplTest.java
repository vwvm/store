package org.blackbox.store.services.service.impl;

import org.blackbox.ApiApplication;
import org.blackbox.store.beans.entity.Product;
import org.blackbox.store.beans.entity.ProductImg;
import org.blackbox.store.beans.entity.ProductSku;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.mapper.ProductImgMapper;
import org.blackbox.store.mappers.mapper.ProductMapper;
import org.blackbox.store.mappers.mapper.ProductSkuMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductImgMapper productImgMapper;

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Test
    void recommendProductsList() {
    }

    @Test
    void ProductBasicInfo() {
        String productId = "1";
        //1.商品基本信息,product_status = 1表示上架商品
        List<Product> products = productMapper.selectByMap(Map.of("product_id", productId, "product_status", 1));
        if (products.size() > 0) {
            //商品图片
            List<ProductImg> productImgs = productImgMapper.selectByMap(Map.of("item_id", productId));
            //商品套餐
            List<ProductSku> productSkus = productSkuMapper.selectByMap(Map.of("product_id", productId, "status", 1));
            System.out.println(Map.of("products", products, "productImgs", productImgs, "productSkus", productSkus));
        } else {
            System.out.println(new ResultVO(ResStatus.NO, "商品不存在", null));
        }
    }
}