package org.vwvm.paperManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.vwvm.paperManagement.entity.Major;

/**
 * <p>
 * 用于记录学科专业信息 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {

}
