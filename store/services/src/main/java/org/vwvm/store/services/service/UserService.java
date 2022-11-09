package org.vwvm.store.services.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.entity.User;

public interface UserService extends IService<User> {

    Page<User> selectPageVo(Page<User> page, Integer age);
}
