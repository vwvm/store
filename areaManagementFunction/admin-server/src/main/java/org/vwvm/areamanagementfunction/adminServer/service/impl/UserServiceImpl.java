package org.vwvm.areamanagementfunction.adminServer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.ResponseResult;
import org.vwvm.areamanagementfunction.adminServer.service.IUserService;
import org.vwvm.areamanagementfunction.adminServer.entity.User;
import org.vwvm.areamanagementfunction.adminServer.mapper.UserMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public ResponseResult login(User user) {



        return null;
    }
}
