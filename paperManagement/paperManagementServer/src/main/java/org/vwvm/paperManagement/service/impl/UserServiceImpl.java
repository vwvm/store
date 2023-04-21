package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.User;
import org.vwvm.paperManagement.mapper.UserMapper;
import org.vwvm.paperManagement.service.IUserService;

import java.util.Objects;

/**
 * <p>
 * 用于记录系统的所有用户 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    /**
     * @param name 用户名
     * @param pwd  密码
     * @return 结果
     */
    @Override
    public ResultsVO checkLogin(String name, String pwd) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_username", name);
        wrapper.eq("user_password", pwd);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return new ResultsVO(201, "登录失败", null);
        }
        return new ResultsVO(200, "登录成功", user);

    }

    /**
     * @param name 用户名
     * @return User
     */
    @Override
    public User getUser(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_username", name);
        return userMapper.selectOne(wrapper);
    }

    /**
     * @param id id
     * @return User
     */
    @Override
    public ResultsVO getUserById(Integer id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        return ResultsVO.succeed(user);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public ResultsVO getByUsername(String username) {

        if (Strings.isBlank(username)) {
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)){
            return ResultsVO.fail("用户不存在");
        }
        return ResultsVO.succeed(user);
    }

    /**
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public ResultsVO getUserList(Integer currentPage, Integer pageSize) {
        return getUserList(currentPage, pageSize, "", "", "");
    }

    /**
     * @param username
     * @return
     */
    @Override
    public ResultsVO deleteByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("user_username", username);
        int delete = userMapper.delete(userQueryWrapper);
        if (delete > 0){
            return ResultsVO.succeed("操作成功", delete);
        }
        return ResultsVO.fail("未找到用户");
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param findUsername
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultsVO getUserList(Integer currentPage, Integer pageSize, String findUsername, String startTime, String endTime) {

        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (!findUsername.isBlank()){
            userQueryWrapper.like("user_username", findUsername);
        }
        if (!startTime.isBlank()){
            userQueryWrapper.ge("update_time", startTime);
        }
        if (!endTime.isBlank()){
            userQueryWrapper.le("update_time", endTime);
        }
        userMapper.selectPage(page, userQueryWrapper);
        return ResultsVO.succeed(String.valueOf(page.getTotal()), page.getRecords());
    }


}
