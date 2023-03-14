package org.vwvm.store.services.adminService;

import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.adminBean.Member;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author vwvm
 * @since 2022-09-07
 */
public interface IMemberService extends IService<Member> {

    Member loadByName(String name);

    Object countByName(String name);

    Object pager(Long pageNum, Long pageSize);

    Object pagerByName(String name, Long pageNum, Long pageSize);
}
