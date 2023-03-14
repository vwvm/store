package org.vwvm.store.services.service;

import org.vwvm.store.commons.vo.ResultVO;

public interface UsersService {

    ResultVO checkLogin(String userName, String pwd);

    ResultVO usersRegister(String userName, String pwd);
}
