package org.vwvm.areamanagementfunction.adminServer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.ResponseResult;
import org.vwvm.areamanagementfunction.adminServer.entity.User;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
public interface IUserService extends IService<User> {

    ResponseResult login(User user);
}
