package org.vwvm.store.services.service;

import org.vwvm.store.commons.vo.ResultVO;

public interface UsersService {

    public ResultVO checkLogin(String userName, String pwd);

    public ResultVO usersRegister(String userName, String pwd);
}
