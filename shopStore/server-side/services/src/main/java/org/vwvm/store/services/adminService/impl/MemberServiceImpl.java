package org.vwvm.store.services.adminService.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vwvm.store.beans.adminBean.Member;
import org.vwvm.store.mappers.adminMapper.MemberMapper;
import org.vwvm.store.services.adminService.IMemberService;


/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author vwvm
 * @since 2022-09-07
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member loadByName(String name) {
        return  memberMapper.loadByName(name);
    }

    @Override
    public Object countByName(String name) {
        return memberMapper.countByName(name);
    }

    @Override
    public Object pager(Long pageNum, Long pageSize) {
        Long limitA = (pageNum - 1) * pageSize;
        Long limitB = pageNum * pageSize;
        return memberMapper.pager(limitA, limitB);
    }

    @Override
    public Object pagerByName(String name, Long pageNum, Long pageSize) {
        Long limitA = (pageNum - 1) * pageSize;
        Long limitB = pageNum * pageSize;
        return memberMapper.pagerByName(name, limitA, limitB);
    }
}
