package org.vwvm.store.paperManagement.paperManagementServer.service.impl;

import org.vwvm.store.paperManagement.paperManagementServer.entity.Teacher;
import org.vwvm.store.paperManagement.paperManagementServer.mapper.TeacherMapper;
import org.vwvm.store.paperManagement.paperManagementServer.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
