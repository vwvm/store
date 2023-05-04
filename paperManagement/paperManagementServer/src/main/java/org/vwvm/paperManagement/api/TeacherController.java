package org.vwvm.paperManagement.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.service.impl.TeacherServiceImpl;

/**
 * <p>
 * 用于记录教师的详细信息 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Controller
@ResponseBody
@CrossOrigin
@Tag(name = "教师相关接口")
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    TeacherServiceImpl teacherService;


    @GetMapping("/getUser")
    public ResultsVO getUser() {
        return null;
    }

    @Operation(summary = "获取教师")
    @Parameters({
            @Parameter(name = "id", description = "用户名id", required = false),
    })
    @RequestMapping(value = "/getTeacherByUserId", method = RequestMethod.GET)
    public ResultsVO getTeacherByUserId(@RequestParam(value = "id") Integer id) {
        return teacherService.getTeacherByUserId(id);
    }

    @Operation(summary = "获取教师列表")
    @Parameters({
            @Parameter(name = "departmentId", description = "部门id", required = false),
    })
    @RequestMapping(value = "/getTeacherList", method = RequestMethod.GET)
    public ResultsVO getTeacherList(
            @RequestParam(value = "departmentId", required = false) Integer departmentId
    ) {

        return teacherService.getTeacherList(departmentId);
    }
}
