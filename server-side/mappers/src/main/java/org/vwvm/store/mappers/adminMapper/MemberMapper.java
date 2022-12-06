package org.vwvm.store.mappers.adminMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.vwvm.store.beans.adminBean.Member;
import springfox.documentation.annotations.Cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author vwvm
 * @since 2022-09-07
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member>{


    Member loadByName(String name);

//    @Override
//    @Cacheable("by_id")
//    Member selectById(Serializable id);

    Object countByName(String name);

    List<Member> pager(Long pageNum, Long pageSize);

    List<Member> pagerByName(String name, Long pageNum, Long pageSize);
}
