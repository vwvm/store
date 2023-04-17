package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.entity.School;
import org.vwvm.paperManagement.mapper.SchoolMapper;
import org.vwvm.paperManagement.service.ISchoolService;

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
