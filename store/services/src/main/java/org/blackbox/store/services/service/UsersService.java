package org.blackbox.store.services.service;

import org.blackbox.store.beans.entity.User;
import org.blackbox.store.commons.vo.ResultVO;

public interface UsersService {

    public ResultVO checkLogin(String userName, String pwd);

    public ResultVO usersRegister(String userName, String pwd);
}
