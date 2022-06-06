package org.blackbox.store.services.service.impl;

import org.blackbox.store.beans.entity.Users;
import org.blackbox.store.commons.utils.MD5Utils;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.dao.UsersDao;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    @Transactional
    public ResultVO checkLogin(String name, String pwd) {

        //根据账户名查询信息
        Users users = usersDao.queryUserByName(name);

        if (users == null) {
            return new ResultVO(10001, "用户名不存在", null);
        } else {
            String md5Pwd = MD5Utils.md5(pwd);
            //密码匹配
            if (users.getPassword().equals(md5Pwd)) {
                return new ResultVO(10000, "登录成功", null);

                //密码错误
            } else {
                return new ResultVO(10002, "登录失败，密码错误！", null);
            }

        }


    }

    @Override
    public ResultVO usersRegister(String userName, String pwd) {
        synchronized (this) {
            //查询用户名是否被注册
            Users users = usersDao.queryUserByName(userName);

            if (users == null) {
                String md5Pwd = MD5Utils.md5(pwd);
                users = new Users();
                users.setUsername(userName);
                users.setPassword(md5Pwd);
                users.setUserRegtime(new Date());
                users.setUserModtime(new Date());
                int i = usersDao.insertUsers(users);
                if (i > 0) {
                    return new ResultVO(10000, "注册成功", null);
                } else {
                    return new ResultVO(10002, "注册失败", null);
                }
            } else {
                return new ResultVO(10001, "用户名已经被注册", null);
            }
        }
    }
}
