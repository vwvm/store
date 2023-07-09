package org.vwvm.paperManagement.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Student;
import org.vwvm.paperManagement.service.impl.StudentServiceImpl;

import java.util.List;

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


    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }


    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @Operation(summary = "用户接口")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResultsVO getProjectServiceList(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "role", required = false) Integer role,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "findName", required = false) String findName,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime
    ) {
        return studentService.getStudent(id);
    }


    @Operation(summary = "提交的学生", description = "用于添加和编辑")
    @Parameters({
    })
    @ResponseBody
    @RequestMapping(value = "/student", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResultsVO postStudent(
            @RequestBody Student student,
            HttpServletRequest httpServletRequest
    ) {
        boolean b = false;
        switch (httpServletRequest.getMethod()) {
            case "POST" -> b = studentService.save(student);
            case "PUT" -> b = studentService.updateById(student);
        }
        return b ? ResultsVO.succeed(student) : ResultsVO.fail("操纵失败");

    }

    @Operation(summary = "根据教师查询学生列表", description = "根据教师查询学生列表用于添加和编辑")
    @Parameters({
            @Parameter(description = "教师id")
    })
    @ResponseBody
    @RequestMapping(value = "/getStudentListByTeacher", method = {RequestMethod.GET})
    public ResultsVO getStudentListByTeacher(
            @RequestParam(value = "teacherId") Integer teacherId
    ) {


        List<Student> studentList = studentService.getStudentListByTeacher(teacherId);
        return ResultsVO.succeed(studentList);

    }

    @Operation(summary = "获取用户列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/getStudentList", method = RequestMethod.GET)
    public ResultsVO getUserList(
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "findUsername", required = false) String findUsername,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime

    ) {

        return studentService.getStudentList(currentPage, pageSize, findUsername, startTime, endTime);
    }

    @Operation(summary = "获取未添加学生列表")
    @Parameters({
    })
    @ResponseBody
    @RequestMapping(value = "/getNotStudentList", method = RequestMethod.GET)
    public ResultsVO getNotAddList(
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "findUsername", required = false) String findUsername,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime

    ) {
        return studentService.getNotAddList(currentPage, pageSize, findUsername, startTime, endTime);
    }


}
