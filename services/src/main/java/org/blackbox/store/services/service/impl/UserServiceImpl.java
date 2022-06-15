package org.blackbox.store.services.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.User;
import org.blackbox.store.mappers.mapper.UserMapper;
import org.blackbox.store.services.service.UserService;
import org.springframework.stereotype.Service;


@DS("docker1")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Page<User> selectPageVo(Page<User> page, Integer age) {
        return null;
    }
}
