package org.vwvm.store.apiserver.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vwvm.store.apiserver.entity.UserDetail;
import org.vwvm.store.apiserver.param.UserDetailParam;

public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
