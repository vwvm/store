package org.vwvm.store.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.vwvm.store.beans.entity.User;


public interface UserMapper extends BaseMapper<User> {

    public User queryUserByName(String name);

    /**
     * 通过年龄查询用户信息并分页
     * @param page mybatis-plus 提供的Page对象
     * @param age 年龄
     * @return 分页对象
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
