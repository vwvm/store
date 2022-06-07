package org.blackbox.store.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.blackbox.store.beans.entity.User;


public interface UserMapper extends BaseMapper<User> {

    public User queryUserByName(String name);
}
