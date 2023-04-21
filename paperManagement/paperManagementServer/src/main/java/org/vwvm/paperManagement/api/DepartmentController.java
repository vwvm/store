package org.vwvm.paperManagement.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Department;
import org.vwvm.paperManagement.service.impl.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    DepartmentServiceImpl departmentService;

    record PoDepartment(Integer id, String departmentName){};

    @Autowired
    DepartmentController(DepartmentServiceImpl departmentService){
        this.departmentService = departmentService;
    }

    @Operation(summary = "获取用户列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
    public ResultsVO getUserList() {

        List<Department> list = departmentService.list();
        List<PoDepartment> departmentList = new ArrayList<>();
        // BeanUtils.copyProperties(list, departmentList);
        list.forEach(department -> {

        });

        BeanUtils.copyProperties(departmentList, list);
        return ResultsVO.succeed(departmentList);
    }
}
