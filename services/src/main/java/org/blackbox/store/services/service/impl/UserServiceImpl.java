package org.blackbox.store.services.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.User;
import org.blackbox.store.mappers.mapper.UserMapper;
import org.blackbox.store.services.service.UserService;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
