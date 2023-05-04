package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Project;
import org.vwvm.paperManagement.entity.Student;
import org.vwvm.paperManagement.entity.Teacher;
import org.vwvm.paperManagement.mapper.ProjectMapper;
import org.vwvm.paperManagement.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课题信息表 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-07
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    private final ProjectMapper projectMapper;
    private final TeacherServiceImpl teacherService;
    private final StudentServiceImpl studentService;

    @Autowired
    ProjectServiceImpl(ProjectMapper projectMapper, TeacherServiceImpl teacherService, StudentServiceImpl studentService) {
        this.projectMapper = projectMapper;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    /**
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize) {

        return getProjectServiceList(pageNum, pageSize, 0, 0, "");
    }

    /**
     * @param projectName     课题名称
     * @param projectDescribe 课题来源
     * @param projectFrom     课题描述
     * @return
     */
    public ResultsVO save(String projectName, String projectDescribe, String projectFrom) {
        boolean save = save(new Project(projectName, projectDescribe, projectFrom));
        if (save) {
            return ResultsVO.succeed(projectName + "添加成功");
        }
        return ResultsVO.succeed(projectName + "添加失败");
    }

    /**
     * @param id              id
     * @param projectName     课题名称
     * @param projectDescribe 课题来源
     * @param projectFrom     课题描述
     * @return
     */
    @Override
    public ResultsVO update(Integer id, String projectName, String projectDescribe, String projectFrom) {
        boolean update = updateById(new Project(id, projectName, projectDescribe, projectFrom));
        if (update) {
            return ResultsVO.succeed(projectName + "修改成功");
        }
        return null;
    }

    /**
     * @param project project对象
     * @return
     */
    @Override
    public ResultsVO update(Project project) {

        boolean update = updateById(project);
        if (update) {
            return ResultsVO.succeed(project.getProjectName() + "修改成功");
        }
        return ResultsVO.succeed(project.getProjectName() + "修改失败");
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param role     0 管理员 1 教师 2 学生
     * @param id
     * @param findName
     * @return
     */
    @Override
    public ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize, Integer role, Integer id, String findName) {
        return getProjectServiceList(pageNum, pageSize, role, id, findName, "", "");
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param role      0 管理员 1 教师 2 学生
     * @param id
     * @param findName
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize, Integer role, Integer id, String findName, String startTime, String endTime) {
        Page<Project> dataDownloadPage = new Page<>();
        dataDownloadPage.setCurrent(pageNum).setSize(pageSize);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        queryWrapper.like("project_name", findName);
        if (!startTime.isBlank()) {
            queryWrapper.ge("update_time", startTime);
        }
        if (!endTime.isBlank()) {
            queryWrapper.le("update_time", endTime);
        }
        if (role == 1) {
            queryWrapper.and(o -> o.eq("teacher_id", id).or().isNull("teacher_id"));
        }
        if (role == 2) {
            queryWrapper.and(o -> o.eq("student_id", id).or().isNull("student_id").or().eq("student_id", ""));
        }
        dataDownloadPage = projectMapper.selectPage(dataDownloadPage, queryWrapper);
        List<Project> projectList = dataDownloadPage.getRecords();
        if (role == 1) {
            dataDownloadPage.setRecords(projectList.stream().peek(project ->
                    {
                        if (Objects.nonNull(project.getStudentId()) && !project.getStudentId().isBlank()) {
                            Student teacher = studentService.getById(Integer.parseInt(project.getTeacherId()));
                            if (Objects.nonNull(teacher)) {
                                project.setStudentName(teacher.getStudentName());
                            }
                        }else {
                            project.setStudentName("空");
                        }
                    }
            ).toList());
        }
        if (role == 2) {
            dataDownloadPage.setRecords(projectList.stream().peek(project ->
                    {
                        if (Objects.nonNull(project.getTeacherId()) && !project.getTeacherId().isBlank()) {
                            Teacher teacher = teacherService.getById(Integer.parseInt(project.getTeacherId()));
                            if (Objects.nonNull(teacher)) {
                                project.setTeacherName(teacher.getTeacherName());
                            }
                        }
                    }
            ).toList());
        }
        return ResultsVO.succeed(200, String.valueOf(dataDownloadPage.getTotal()), dataDownloadPage.getRecords());
    }

    record POProject(String s) {
    }
}
