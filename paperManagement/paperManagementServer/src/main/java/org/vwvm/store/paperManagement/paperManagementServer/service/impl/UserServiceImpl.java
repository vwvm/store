package org.vwvm.store.paperManagement.paperManagementServer.service.impl;

import org.vwvm.store.paperManagement.paperManagementServer.entity.User;
import org.vwvm.store.paperManagement.paperManagementServer.mapper.UserMapper;
import org.vwvm.store.paperManagement.paperManagementServer.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用于记录系统的所有用户 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
