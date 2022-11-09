package org.vwvm.store.services.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.vwvm.store.beans.entity.User;
import org.vwvm.store.mappers.mapper.UserMapper;
import org.vwvm.store.services.service.UserService;
import org.springframework.stereotype.Service;


@DS("docker1")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Page<User> selectPageVo(Page<User> page, Integer age) {
        return null;
    }
}
