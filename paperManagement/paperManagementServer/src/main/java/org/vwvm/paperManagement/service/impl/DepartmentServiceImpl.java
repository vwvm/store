package org.vwvm.paperManagement.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.entity.Department;
import org.vwvm.paperManagement.mapper.DepartmentMapper;
import org.vwvm.paperManagement.service.IDepartmentService;

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
