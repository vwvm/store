package org.vwvm.store.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.vwvm.store.beans.bean.ProductCategoryBean;
import org.vwvm.store.beans.entity.Product;
import org.vwvm.store.beans.entity.ProductSpecification;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.services.service.IProductCommentsService;
import org.springframework.web.bind.annotation.*;
import org.vwvm.store.services.service.impl.ProductCategoryServiceImpl;
import org.vwvm.store.services.service.impl.ProductServiceImpl;
import org.vwvm.store.services.service.impl.ProductSpecificationServiceImpl;

import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "商品信息相关表", tags = "商品管理")
public class ProductController {
    @Resource
    ProductServiceImpl productService;
    @Resource
    IProductCommentsService productCommentsService;
    @Resource
    ProductSpecificationServiceImpl productSpecificationService;
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
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "str", value = "商品内容", required = false),
            @ApiImplicitParam(dataType = "string", name = "type", value = "商品类别", required = false),
            @ApiImplicitParam(dataType = "Integer", name = "str", value = "商品个数", required = false),
    })
    public ResultVO productSearch(@RequestParam String str, String type, Integer limit) {
        List<Product> products = productService.productSearch(str, type, limit);
        return new ResultVO(ResStatus.OK, "ok", products);
    }

    @ApiOperation(value = "搜索商品带页码")
    @GetMapping("/productSearchPage")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "str", value = "商品内容", required = false),
            @ApiImplicitParam(dataType = "String", name = "type", value = "商品类别", required = false),
            @ApiImplicitParam(dataType = "Integer", name = "pageNum", value = "当前页", required = false),
            @ApiImplicitParam(dataType = "Integer", name = "pageSize", value = "每页大小", required = false),
    })
    public ResultVO productSearchPage(@RequestParam String str, String type, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = productService.productSearchPage(str, type, pageNum, pageSize);
        return new ResultVO(ResStatus.OK, "ok", productPage);
    }

    @ApiOperation(value = "根据类别搜索商品")
    @GetMapping("/productSearchType")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "type", value = "商品类别", required = true),
    })
    public ResultVO productSearchType(@RequestParam String type) {
        List<Product> products = productService.productSearchType(type);
        System.out.println(products);
        return new ResultVO(ResStatus.OK, "ok", products);
    }

    @ApiOperation(value = "根据id搜索商品规格")
    @GetMapping("/productSpecificationId")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Integer", name = "productId", value = "当前id", required = true),
    })
    public ResultVO productSpecificationId(@RequestParam Integer productId) {
        ProductSpecification productSpecification = productSpecificationService.getProductSpe(productId);
        return new ResultVO(ResStatus.OK, "ok", productSpecification);
    }

    @ApiOperation(value = "根据id搜索商品")
    @GetMapping("/productId")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Integer", name = "productId", value = "当前id", required = true),
    })
    public ResultVO productId(@RequestParam Integer productId) {
        Product product = productService.productId(productId);
        return new ResultVO(ResStatus.OK, "ok", product);
    }


}
