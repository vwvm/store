package org.blackbox.store.services.service;

import org.blackbox.store.commons.vo.ResultVO;

public interface UsersService {

    public ResultVO checkLogin(String userName, String pwd);
}
