package org.vwvm.paperManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.*;
import org.vwvm.paperManagement.entity.requsetEntity.RqPage;
import org.vwvm.paperManagement.mapper.DataDownloadMapper;
import org.vwvm.paperManagement.service.IDataDownloadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 资源下载信息信息 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-03
 */
@Service
public class DataDownloadServiceImpl extends ServiceImpl<DataDownloadMapper, DataDownload> implements IDataDownloadService {

    private final DataDownloadMapper dataDownloadMapper;
    private final TeacherServiceImpl teacherService;
    private final StudentServiceImpl studentService;
    private final IndexDownloadServiceImpl indexDownloadService;
    private final ProjectServiceImpl projectService;

    @Autowired
    public DataDownloadServiceImpl(DataDownloadMapper dataDownloadMapper, TeacherServiceImpl teacherService, StudentServiceImpl studentService, IndexDownloadServiceImpl indexDownloadService, ProjectServiceImpl projectService) {
        this.dataDownloadMapper = dataDownloadMapper;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.indexDownloadService = indexDownloadService;
        this.projectService = projectService;
    }


    /**
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public ResultsVO dataDownloadList(Integer pageNum, Integer pageSize) {

        Page<DataDownload> dataDownloadPage = new Page<>();
        dataDownloadPage.setCurrent(pageNum).setSize(pageSize);
        QueryWrapper<DataDownload> queryWrapper = new QueryWrapper<>();
        dataDownloadPage = dataDownloadMapper.selectPage(dataDownloadPage, queryWrapper);
        if (Objects.isNull(dataDownloadPage)) {
            return ResultsVO.fail("错误");
        }
        return ResultsVO.succeed(200, String.valueOf(dataDownloadPage.getTotal()), dataDownloadPage.getRecords());
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @return
     */
    @Override
    public ResultsVO getDownloadListByTeacher(Integer pageNum, Integer pageSize, String teacherId) {


        Page<DataDownload> dataDownloadPage = new Page<>();
        dataDownloadPage.setCurrent(pageNum).setSize(pageSize);
        QueryWrapper<DataDownload> queryWrapper = new QueryWrapper<>();
        dataDownloadPage = dataDownloadMapper.selectPage(dataDownloadPage, queryWrapper);
        if (Objects.isNull(dataDownloadPage)) {
            return ResultsVO.fail("错误");
        }

        return ResultsVO.succeed(200, String.valueOf(dataDownloadPage.getTotal()), dataDownloadPage.getRecords());
    }

    public record RpDownloadListByTeacher(Integer id, String dataUrl, String proposalName,
                                          Integer studentId, String studentName, Integer state) {
    }

    /**
     * @param rqPage
     * @return
     */
    @Override
    public List<RpDownloadListByTeacher> getDownloadListByTeacher(RqPage rqPage) {
        // 先获取学生
        List<Student> studentList = studentService.getStudentListByTeacher(rqPage.getId(), rqPage.getFindStr());


        Page<IndexDownload> dataDownloadPage = new Page<>();
        dataDownloadPage.setCurrent(rqPage.getPageNum()).setSize(rqPage.getPageSize());
        QueryWrapper<IndexDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        if (!rqPage.getStartTime().isBlank()) {
            queryWrapper.ge("update_time", rqPage.getStartTime());
        }
        if (!rqPage.getEndTime().isBlank()) {
            queryWrapper.le("update_time", rqPage.getEndTime());
        }
        queryWrapper.eq("data_index1", rqPage.getIndex1());
        if (rqPage.getIndex1() != 3) {
            queryWrapper.eq("data_index2", rqPage.getIndex2());
        }


        List<RpDownloadListByTeacher> rpList = new ArrayList<>();
        DataDownload dataDownload;
        RpDownloadListByTeacher rpDownloadListByTeacher;
        for (Student student : studentList) {
            queryWrapper.eq("user_id", student.getUserId());
            List<IndexDownload> list = indexDownloadService.list(queryWrapper);
            for (IndexDownload i : list) {
                dataDownload = dataDownloadMapper.selectById(i.getDataDownloadId());
                rpDownloadListByTeacher = new RpDownloadListByTeacher(i.getId(), dataDownload.getDataUrl()
                        , dataDownload.getDataName(), student.getId(), student.getStudentName(), i.getAuditMark());
                rpList.add(rpDownloadListByTeacher);
            }
        }

        return rpList;
    }
}
