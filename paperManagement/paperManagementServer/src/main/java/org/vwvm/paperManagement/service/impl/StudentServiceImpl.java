package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Major;
import org.vwvm.paperManagement.entity.Student;
import org.vwvm.paperManagement.entity.Teacher;
import org.vwvm.paperManagement.entity.User;
import org.vwvm.paperManagement.mapper.MajorMapper;
import org.vwvm.paperManagement.mapper.StudentMapper;
import org.vwvm.paperManagement.mapper.TeacherMapper;
import org.vwvm.paperManagement.mapper.UserMapper;
import org.vwvm.paperManagement.service.IStudentService;

import java.util.List;

/**
 * <p>
 * 用于记录教师的详细信息 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    private final StudentMapper studentMapper;
    private final MajorMapper majorMapper;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, MajorMapper majorMapper, TeacherMapper teacherMapper, UserMapper userMapper) {
        this.studentMapper = studentMapper;
        this.majorMapper = majorMapper;
        this.teacherMapper = teacherMapper;
        this.userMapper = userMapper;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResultsVO getStudent(Integer id) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("user_id", id);
        studentQueryWrapper.last("limit 1");
        Student student = studentMapper.selectOne(studentQueryWrapper);
        majorQueryWrapper.eq("id", student.getMajorId());
        majorQueryWrapper.last("limit 1");
        Major major = majorMapper.selectOne(majorQueryWrapper);
        student.setMajorName(major.getMajorName());
        Teacher teacher = teacherMapper.selectById(student.getTeacherId());
        student.setTeacherId(teacher.getTeacherName());
        return ResultsVO.succeed(student);
    }

    /**
     * @param teacherId
     * @return
     */
    @Override
    public List<Student> getStudentListByTeacher(Integer teacherId) {

        return getStudentListByTeacher(teacherId, "");

    }

    /**
     * @param teacherId
     * @param findStr
     * @return
     */
    @Override
    public List<Student> getStudentListByTeacher(Integer teacherId, String findStr) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("teacher_id", teacherId);
        if (!findStr.isBlank()){
            studentQueryWrapper.like("student_name", findStr);
        }
        return studentMapper.selectList(studentQueryWrapper);

    }

    /**
     * @param currentPage
     * @param pageSize
     * @param findUsername
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultsVO getStudentList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime) {
        Page<Student> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Student> userQueryWrapper = new QueryWrapper<>();
        if (!findUsername.isBlank()){
            userQueryWrapper.like("user_username", findUsername);
        }
        if (!startTime.isBlank()){
            userQueryWrapper.ge("update_time", startTime);
        }
        if (!endTime.isBlank()){
            userQueryWrapper.le("update_time", endTime);
        }
        studentMapper.selectPage(page, userQueryWrapper);
        return ResultsVO.succeed(String.valueOf(page.getTotal()), page.getRecords());
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param findUsername
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultsVO getNotAddList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("where JSON_EXTRACT(user_roles,'$[0]') = 'ROLE_student' and id not in (select user_id from student)");
        List<User> users = userMapper.selectList(queryWrapper);
        return ResultsVO.succeed(users);
    }
}
