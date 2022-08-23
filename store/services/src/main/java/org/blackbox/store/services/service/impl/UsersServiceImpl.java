package org.blackbox.store.services.service.impl;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.blackbox.store.beans.entity.Users;
import org.blackbox.store.commons.utils.JwtUtils;
import org.blackbox.store.commons.utils.MD5Utils;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.dao.UsersDao;
import org.blackbox.store.services.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

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
            return new ResultVO(ResStatus.NO, "用户名不存在", null);
        } else {
            String md5Pwd = MD5Utils.md5(pwd);
            //密码匹配
            if (users.getPassword().equals(md5Pwd)) {
                //使用jwt生成规则
                HashMap<String, Object> map = new HashMap<>();
                map.put("userId", users.getUserId());
                map.put("username", users.getUsername());
                String token = JwtUtils.createJwt(map);
                return new ResultVO(ResStatus.OK, token, users);

                //密码错误
            } else {
                return new ResultVO(ResStatus.NO, "登录失败，密码错误！", null);
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
                users.setUserImg("img/default.png");
                int i = usersDao.insertUsers(users);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "成功完成注册", null);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败", null);
                }
            } else {
                return new ResultVO(ResStatus.NO, "用户名已经被注册", null);
            }
        }
    }
}
