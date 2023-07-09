package org.vwvm.paperManagement.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Student;

import java.util.List;

/**
 * <p>
 * 用于记录教师的详细信息 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
public interface IStudentService extends IService<Student> {

    ResultsVO getStudent(Integer id);

    List<Student> getStudentListByTeacher(Integer teacherId);

    List<Student> getStudentListByTeacher(Integer id, String findStr);

    ResultsVO getStudentList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime);

    ResultsVO getNotAddList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime);
}
