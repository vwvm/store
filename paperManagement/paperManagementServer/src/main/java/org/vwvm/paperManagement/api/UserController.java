package org.vwvm.paperManagement.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.service.impl.UserServiceImpl;

/**
 * <p>
 * 用于记录系统的所有用户 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Controller
@CrossOrigin
@Tag(name = "用户管理", description = "提供用户的登录和管理功能")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl usersService;

    @RequestMapping(value = "/loginSecurity", method = RequestMethod.POST)
    @ResponseBody
    public String loginSecurity(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "token") String token,
                                @RequestParam(value = "captcha") String captcha
    ) {
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        return username + password + token + captcha;
    }

    @Operation(summary = "获取用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户名id", required = false),
    })
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public ResultsVO getUserById(@RequestParam(value = "id") Integer id) {
        return usersService.getUserById(id);
    }

    @Operation(summary = "获取用户列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
    })
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ResultsVO getUserList(
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        return usersService.getUserList(currentPage, pageSize);
    }

    @GetMapping("/no")
    @ResponseBody
    public String test() {
        return "请先登录";
    }

    @PostMapping("/userRule")
    @ResponseBody
    public String testUserPost(String a, String b) {


        return a + "给" + b + "打钱";
    }

    @PostMapping("/adminRule")
    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    public String testAdminPost() {
        return "admin 访问成功";
    }

    @PostMapping("/teacherRule")
    @PreAuthorize("hasAuthority('teacher')")
    @ResponseBody
    public String testTeacherPost() {
        return "teacher 访问成功";
    }

    @GetMapping("/view")
    public String view() {
        return "login";
    }
}
