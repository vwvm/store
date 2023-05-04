package org.vwvm.paperManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Teacher;

/**
 * <p>
 * 用于记录教师的详细信息 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
public interface ITeacherService extends IService<Teacher> {

    ResultsVO getTeacherByUserId(Integer id);

    ResultsVO getTeacherByUserId(Integer id, Integer schoolId);

    ResultsVO getTeacherList(Integer schoolId);
}
