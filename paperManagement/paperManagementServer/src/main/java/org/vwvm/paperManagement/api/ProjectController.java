package org.vwvm.paperManagement.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Project;
import org.vwvm.paperManagement.service.impl.DataDownloadServiceImpl;
import org.vwvm.paperManagement.service.impl.ProjectServiceImpl;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 课题信息表 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-07
 */
@Controller
@ResponseBody
@CrossOrigin
@Tag(name = "课题信息")
@RequestMapping("/project")
public class ProjectController {

    @Resource
    ProjectServiceImpl projectService;


    @Operation(summary = "选题列表接口")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
            @Parameter(name = "role", description = "0 管理员 1 教师 2 学生")
    })
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    public ResultsVO getProjectServiceList(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "role", required = false) Integer role,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "findName", required = false) String findName,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime
    ) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 10;
        }

        return projectService.getProjectServiceList(pageNum, pageSize, role, id, findName, startTime, endTime);
    }

    @Operation(summary = "教师提交选题接口")
    @RequestMapping(value = "/postProject", method = RequestMethod.POST)
    public ResultsVO postProject(@RequestBody Project project) {

        return ResultsVO.succeed(projectService.save(project));
    }

    record ReProject(String studentId) {
    }

    @Operation(summary = "教师提交选题接口")
    @RequestMapping(value = "/project", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResultsVO projectOne(@RequestParam Project project, HttpServletRequest httpServletRequest) {

        switch (httpServletRequest.getMethod()) {
            case "DELETE" -> {
                boolean b = projectService.removeById(project.getId());
                return b ? ResultsVO.succeed("操作成功") : ResultsVO.fail("操纵失败");
            }
            case "GET" -> {
                Project byId = projectService.getById(project.getId());
                return ResultsVO.succeed(byId);
            }
        }
        return ResultsVO.fail("操纵失败");

    }

    @ResponseBody
    @RequestMapping(value = "/selectedProject", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResultsVO selectedProject(@RequestParam Integer studentId, HttpServletRequest httpServletRequest) {

        switch (httpServletRequest.getMethod()) {
            case "DELETE" -> {
                boolean b = projectService.removeById(studentId);
                return b ? ResultsVO.succeed("操作成功") : ResultsVO.fail("操纵失败");
            }
            case "GET" -> {
                QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("student_id", studentId);
                queryWrapper.eq("project_state", 1);
                queryWrapper.isNotNull("teacher_id");
                queryWrapper.last("limit 1");
                Project one = projectService.getOne(queryWrapper);
                return ResultsVO.succeed(one);
            }
        }
        return ResultsVO.fail("操纵失败");

    }


    @Operation(summary = "编辑接口")
    @RequestMapping(value = "/putProject", method = RequestMethod.PUT)
    public ResultsVO putProject(@RequestBody Project project
    ) {
        return projectService.update(project);
    }
}
