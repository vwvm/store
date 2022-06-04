package org.blackbox.store.services.service.impl;

import org.blackbox.store.beans.entity.Users;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.dao.UsersDao;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    public ResultVO checkLogin(String name, String pwd) {
        //根据账户名查询信息
        Users users = usersDao.queryUserByName(name);

        if (users == null) {
            return new ResultVO(1, "用户名不存在",null);
        }else {
            //密码匹配
            if (users.getPassword().equals(pwd)){
                return new ResultVO(2, "登录成功" , null);

            //密码错误
            }else {
                return new ResultVO(1, "用户名不存在",null);
            }

        }
    }
}
