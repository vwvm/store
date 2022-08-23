package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation("购物车列表接口接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "token", value = "授权令牌", required = true),
    })
    @GetMapping("/list")
    public ResultVO listCarts(String token){
        //获取token

        
        //校验token

        return new ResultVO(ResStatus.OK, "success", null);
    }
}
