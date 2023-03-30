package org.vwvm.store.paperManagement.paperManagementServer.service.impl;

import org.vwvm.store.paperManagement.paperManagementServer.entity.Department;
import org.vwvm.store.paperManagement.paperManagementServer.mapper.DepartmentMapper;
import org.vwvm.store.paperManagement.paperManagementServer.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
