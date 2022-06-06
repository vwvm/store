package org.blackbox.store.mappers.dao;

import org.blackbox.store.beans.entity.Users;

public interface UsersDao {

    /**
     * 根据用户名查询用户信息
     * @param name 用户名
     * @return 用户信息
     */
    public Users queryUserByName(String name);

    /**
     * 用户注册
     * @param users 用户信息
     * @return 成功结果
     */
    public int insertUsers(Users users);
}
