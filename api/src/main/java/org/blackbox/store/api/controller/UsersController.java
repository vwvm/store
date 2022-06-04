package org.blackbox.store.api.controller;

import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * @param name 用户名
     * @param pwd 密码
     * @return 结果
     */
    @RequestMapping("/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password", defaultValue = "1111") String pwd){
        return usersService.checkLogin(name, pwd);
    }
}
