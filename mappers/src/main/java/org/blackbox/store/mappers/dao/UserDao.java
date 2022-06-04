package org.blackbox.store.mappers.dao;

import org.blackbox.store.beans.entity.User;

public interface UserDao {

    public User queryUserByName(String name);
}
