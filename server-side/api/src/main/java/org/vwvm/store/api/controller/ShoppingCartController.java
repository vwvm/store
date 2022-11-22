package org.vwvm.store.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.vwvm.store.beans.entity.ShoppingCart;
import org.vwvm.store.commons.utils.JwtUtils;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.services.service.IShoppingCartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 购物车  前端控制器
 * </p>
 * 1.0.0
 * @author BlackBox
 * @since 2022-08-23
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/shoppingCart")
@Api(value = "提供购物车相关接口", tags = "购物车")
public class ShoppingCartController {

    @Resource
    private IShoppingCartService shoppingCartService;

    @ApiOperation("购物车列表接口接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "token", value = "授权令牌", required = true),
    })
    @GetMapping("/list")
    public ResultVO listCarts(String token){
        //获取token
        if (token == null){
            return new ResultVO(ResStatus.NO, "请先登录", null);
        }
        //校验token
        if (JwtUtils.parseJwt(token) == 0){
            return new ResultVO(ResStatus.OK, "token校验成功", null);
        }

        return new ResultVO(ResStatus.NO, "token不合法", null);
    }
    @ApiOperation("购物车列表添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ShoppingCart", name = "shoppingCart", value = "商品信息", required = true),
    })
    @PostMapping("/addShoppingCart")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart shoppingCart, @RequestHeader("token") String token){
        return shoppingCartService.addShoppingCart(shoppingCart);
    }
}
