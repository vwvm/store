package org.vwvm.paperManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.vwvm.paperManagement.entity.Student;

/**
 * <p>
 * 用于记录教师的详细信息 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
