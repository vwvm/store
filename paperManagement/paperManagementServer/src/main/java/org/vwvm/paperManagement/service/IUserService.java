package org.vwvm.paperManagement.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.User;

/**
 * <p>
 * 用于记录系统的所有用户 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
public interface IUserService extends IService<User> {

    ResultsVO checkLogin(String name, String pwd);

    User getUser(String name);

    ResultsVO getUserById(Integer id);

    ResultsVO getByUsername(String username);

    ResultsVO getUserList(Integer currentPage, Integer pageSize);

    ResultsVO deleteByUsername(String username);

    ResultsVO getUserList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime);
}
