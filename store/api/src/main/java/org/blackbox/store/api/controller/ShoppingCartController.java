package org.blackbox.store.api.controller;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.commons.utils.Base64Utils;
import org.blackbox.store.commons.utils.JwtUtils;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
        if (token == null){
            return new ResultVO(ResStatus.NO, "请先登录", null);
        }
        //校验token
        if (JwtUtils.parseJwt(token)){
            return new ResultVO(ResStatus.OK, "token校验成功", null);
        }

        return new ResultVO(ResStatus.NO, "token不合法", null);
    }
}
