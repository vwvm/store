package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.IProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

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
@Api(value = "商品信息相关表",tags = "商品管理")
public class ProductController {

    @Resource
    IProductService productService;

    @ApiOperation("商品基本信息接口")
    @GetMapping("/detail-info/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "pid", value = "商品id", required = true),
    })
    public ResultVO getProductBasicInfo(@PathVariable String pid){
        return productService.getProductBasicInfo(pid);
    }
}