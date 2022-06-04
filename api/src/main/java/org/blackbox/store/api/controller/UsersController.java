package org.blackbox.store.api.controller;

import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping("/login")
    public ResultVO login(String name, String pwd){
        return usersService.checkLogin(name, pwd);
    }
}
