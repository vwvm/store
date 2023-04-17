package org.vwvm.paperManagement.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用于记录教师的详细信息 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
