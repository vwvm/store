package org.vwvm.store.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.bean.ProductCategoryBean;
import org.vwvm.store.beans.entity.Product;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.services.service.IProductCommentsService;
import org.vwvm.store.services.service.IProductSpecificationService;
import org.vwvm.store.services.service.impl.ProductCategoryServiceImpl;
import org.vwvm.store.services.service.impl.ProductServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {
    @Resource
    ProductServiceImpl productService;
    @Resource
    IProductCommentsService productCommentsService;
    @Resource
    IProductSpecificationService productSpecificationService;
    @Resource
    ProductCategoryServiceImpl productCategoryService;

    @ApiOperation("商品基本信息接口")
    @GetMapping("/detail-info/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "pid", value = "商品id", required = true),
    })
    public ResultVO getProductBasicInfo(@PathVariable String pid) {
        return productService.getProductBasicInfo(pid);
    }

    @ApiOperation("商品详细信息接口")
    @GetMapping("/detail-params/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "pid", value = "商品id", required = true),
    })
    public ResultVO getProductParams(@PathVariable String pid) {
        return productService.getProductParamsById(pid);
    }

    @ApiOperation("商品评价信息接口")
    @GetMapping("/detail-comments/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "pid", value = "商品id", required = true),
    })
    public ResultVO productComments(@PathVariable String pid) {
        return productCommentsService.productCommentsList(pid);
    }

    @ApiOperation("商品前六的接口接口")
    @GetMapping("/top6/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "pid", value = "商品主类别id", required = true),
    })
    public ResultVO productTop6(@PathVariable Integer pid) {
        return productService.selectCategoryProducts(pid);
    }


    @ApiOperation(value = "获取商品信息")
    @GetMapping("/productSpecification")
    public ResultVO productSpecificationList() {

        return new ResultVO(ResStatus.OK, "ok", productSpecificationService.list());
    }

    @ApiOperation(value = "获取商品类别信息")
    @GetMapping("/productCategory")
    public ResultVO productCategoryList() {
        List<ProductCategoryBean> productCategoryBeans = productCategoryService.productCategory();
        return new ResultVO(ResStatus.OK, "ok", productCategoryBeans);
    }

    @ApiOperation(value = "获取商品列表")
    @GetMapping("/product")
    public ResultVO productList() {

        return new ResultVO(ResStatus.OK, "ok", productService.list());
    }

    @ApiOperation(value = "搜索商品")
    @GetMapping("/productSearch")
    public ResultVO productSearch(@RequestParam String str) {
        System.out.println(str);
        List<Product> products = productService.productSearch(str);
        return new ResultVO(ResStatus.OK, "ok", products);
    }

    @ApiOperation(value = "根据类别搜索商品")
    @GetMapping("/productSearchType")
    @ParameterizedTest
    @CsvSource({"'11',1", "'2', 2"})
    public void testProductSearchType(@RequestParam String type, int i) {
        System.out.println("i:" + i);
        List<Product> products = productService.productSearchType(type);
        System.out.println(products);
    }
}
