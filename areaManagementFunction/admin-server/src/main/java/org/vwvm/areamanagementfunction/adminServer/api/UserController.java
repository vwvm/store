package org.vwvm.areamanagementfunction.adminServer.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.MenuVO;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.ResponseResult;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.StatusVO;
import org.vwvm.areamanagementfunction.adminServer.service.impl.UserServiceImpl;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username") String username,@RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        return new ResponseResult(StatusVO.OK, "a", "jj");
    }

    @GetMapping("/menus")
    public ResponseResult menus(){
        return new ResponseResult(StatusVO.OK, "", List.of(
                new MenuVO("域区管理", "/home/region", null),
                new MenuVO("城市信息", "/home/city", null)
                ));
    }

}
