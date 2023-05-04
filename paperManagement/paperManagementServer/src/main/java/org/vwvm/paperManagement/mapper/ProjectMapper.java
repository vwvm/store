package org.vwvm.paperManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.vwvm.paperManagement.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课题信息表 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-07
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

}
