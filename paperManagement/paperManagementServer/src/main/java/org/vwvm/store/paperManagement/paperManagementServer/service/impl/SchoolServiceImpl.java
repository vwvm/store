package org.vwvm.store.paperManagement.paperManagementServer.service.impl;

import org.vwvm.store.paperManagement.paperManagementServer.entity.School;
import org.vwvm.store.paperManagement.paperManagementServer.mapper.SchoolMapper;
import org.vwvm.store.paperManagement.paperManagementServer.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用于记录学校信息 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

}
