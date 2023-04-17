package org.vwvm.paperManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.vwvm.paperManagement.entity.User;

/**
 * <p>
 * 用于记录系统的所有用户 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
