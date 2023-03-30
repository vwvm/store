package org.vwvm.store.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.entity.Users;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.services.service.UsersService;


@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
public class UsersControllerTest {

    @Resource
    private UsersService usersService;


    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户登录账户", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户登录密码", required = true),
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password") String pwd) {
        return usersService.checkLogin(name, pwd);
    }


    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户注册账户", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户注册密码", required = true),
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultVO register(@RequestBody Users users) {
        return usersService.usersRegister(users.getUsername(), users.getPassword());
    }



}
