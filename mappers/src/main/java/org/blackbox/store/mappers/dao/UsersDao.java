package org.blackbox.store.mappers.dao;

import org.blackbox.store.beans.entity.Users;

public interface UsersDao {

    public Users queryUserByName(String name);
}
