package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.entity.Student;
import org.vwvm.paperManagement.mapper.StudentMapper;
import org.vwvm.paperManagement.service.IStudentService;

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

}
