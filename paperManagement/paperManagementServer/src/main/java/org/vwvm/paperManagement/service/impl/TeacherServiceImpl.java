package org.vwvm.paperManagement.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Teacher;
import org.vwvm.paperManagement.mapper.TeacherMapper;
import org.vwvm.paperManagement.service.ITeacherService;

import java.util.Objects;

/**
 * <p>
 * 用于记录教师的详细信息 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Resource
    TeacherMapper teacherMapper;

    /**
     * @param id
     * @return
     */
    @Override
    public ResultsVO getTeacher(Integer id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        if (!Objects.isNull(teacher)){
            return ResultsVO.succeed(JSON.toJSON(teacher));
        }
        return ResultsVO.fail("不存在");
    }
}
