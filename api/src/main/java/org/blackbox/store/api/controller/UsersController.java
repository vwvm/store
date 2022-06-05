package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.beans.entity.Users;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/users")
@Api(value = "提供用户的登录和管理功能",tags = "用户管理")
public class UsersController {

    @Resource
    private UsersService usersService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户登录账户", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户登录密码", required = false, defaultValue = "1111"),
    })
    @RequestMapping("/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password", defaultValue = "1111") String pwd){
        return usersService.checkLogin(name, pwd);
    }

    @ApiOperation("用户注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultVO register(Users users){

        return new ResultVO(10000, "successes", null);
    }
}
