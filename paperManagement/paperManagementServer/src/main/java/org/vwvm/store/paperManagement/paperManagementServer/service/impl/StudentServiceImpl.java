package org.vwvm.store.paperManagement.paperManagementServer.service.impl;

import org.vwvm.store.paperManagement.paperManagementServer.entity.Student;
import org.vwvm.store.paperManagement.paperManagementServer.mapper.StudentMapper;
import org.vwvm.store.paperManagement.paperManagementServer.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
