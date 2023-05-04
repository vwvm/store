package org.vwvm.paperManagement.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Teacher;
import org.vwvm.paperManagement.mapper.TeacherMapper;
import org.vwvm.paperManagement.service.ITeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用于记录教师的详细信息 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {


    private final TeacherMapper teacherMapper;
    private Integer id;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    /**
     * @param id
     * @param teacherName 教师名字
     */
    record PoTeacher(Integer id, String teacherName){};


    /**
     * @param id 用户名id
     * @return
     */
    @Override
    public ResultsVO getTeacherByUserId(Integer id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        queryWrapper.last("limit 1");
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        if (!Objects.isNull(teacher)){
            return ResultsVO.succeed(teacher);
        }
        return ResultsVO.fail("不存在");
    }

    /**
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public ResultsVO getTeacherByUserId(Integer id, Integer schoolId) {
        return null;
    }


    /**
     * @return
     */
    @Override
    public ResultsVO getTeacherList(Integer department_id) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("department_id", department_id);
        List<Teacher> teachers = teacherMapper.selectList(teacherQueryWrapper);
        List<PoTeacher> poTeacherList = new ArrayList<>();
        teachers.forEach(teacher -> {
            poTeacherList.add(new PoTeacher(teacher.getId(), teacher.getTeacherName()));
        });

        return ResultsVO.succeed(poTeacherList);
    }
}
