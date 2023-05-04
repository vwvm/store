package org.vwvm.paperManagement.service;

import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课题信息表 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-07
 */
public interface IProjectService extends IService<Project> {

    ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize);

    ResultsVO save(String projectName, String projectDescribe, String projectFrom);

    ResultsVO update(Integer id, String projectName, String projectDescribe, String projectFrom);

    ResultsVO update(Project project);

    ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize, Integer role, Integer id, String findName);

    ResultsVO getProjectServiceList(Integer pageNum, Integer pageSize, Integer role, Integer id, String findName, String startTime, String endTime);
}
