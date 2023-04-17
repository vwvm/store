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
    public ResponseResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        return new ResponseResult(StatusVO.OK, "a", "jj");
    }

    @GetMapping("/menus")
    public ResponseResult menus() {
        return new ResponseResult(StatusVO.OK, "", List.of(
                new MenuVO("图形管理", "/home/graph", null),
                new MenuVO("进货管理", "/home/city", null),
                new MenuVO("销售管理", "/home/city", null),
                new MenuVO("零售管理", "/home/region", null),
                new MenuVO("库存管理", "/home/city", null),
                new MenuVO("钱流管理", "/home/city", null),
                new MenuVO("生产组装", "/home/region", null),
                new MenuVO("总账管理", "/home/city", null),
                new MenuVO("系统维护", "/home/city", null),
                new MenuVO("产品升级", "/home/city", null)
        ));
    }

    @GetMapping("/values")
    public ResponseResult values() {
        return new ResponseResult(StatusVO.OK, "", 123);
    }
}
