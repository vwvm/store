package org.vwvm.paperManagement.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.DataDownload;
import org.vwvm.paperManagement.entity.IndexDownload;
import org.vwvm.paperManagement.entity.Student;
import org.vwvm.paperManagement.entity.requsetEntity.RqPage;
import org.vwvm.paperManagement.service.impl.DataDownloadServiceImpl;
import org.vwvm.paperManagement.service.impl.IndexDownloadServiceImpl;
import org.vwvm.paperManagement.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 资源下载信息信息 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-03
 */
@Controller
@ResponseBody
@CrossOrigin
@Tag(description = "提供资料下载功能", name = "资料下载")
@RequestMapping("/download")
public class DownloadController {


    private final DataDownloadServiceImpl dataDownloadService;
    private final IndexDownloadServiceImpl indexDownloadService;

    private final StudentServiceImpl studentService;

    @Autowired
    public DownloadController(DataDownloadServiceImpl dataDownloadService, IndexDownloadServiceImpl indexDownloadService, StudentServiceImpl studentService) {
        this.dataDownloadService = dataDownloadService;
        this.indexDownloadService = indexDownloadService;
        this.studentService = studentService;
    }


    @ApiOperation("资料下载接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "当前页码", required = false),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "页码大小", required = false),

    })
    @RequestMapping(value = "/dataDownloadList", method = RequestMethod.GET)
    public ResultsVO dataDownloadList(@RequestParam(value = "pageNum") Integer pageNum,
                                      @RequestParam(value = "pageSize") Integer pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 10;
        }

        return dataDownloadService.dataDownloadList(pageNum, pageSize);
    }

    record ReGetAuditMark(String user_id, String data_index1, String data_index2) {
    }

    record PoGetAuditMark(String name, String url, Integer auditMark, String data_describe) {
    }

    @Operation(summary = "获取审核状态")
    @Parameters({
            @Parameter(name = "id", description = "用户名id", required = false),
    })
    @RequestMapping(value = "/getAuditMark", method = RequestMethod.GET)
    public ResultsVO getAuditMark(ReGetAuditMark reGetAuditMark) {

        QueryWrapper<IndexDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", reGetAuditMark.user_id);
        queryWrapper.eq("data_index1", reGetAuditMark.data_index1);
        queryWrapper.eq("data_index2", reGetAuditMark.data_index2);
        List<IndexDownload> list = indexDownloadService.list(queryWrapper);
        PoGetAuditMark poGetAuditMark = null;
        if (!list.isEmpty()) {
            IndexDownload indexDownload = list.get(0);
            DataDownload byId = dataDownloadService.getById(indexDownload.getDataDownloadId());
            poGetAuditMark = new PoGetAuditMark(byId.getDataName(), byId.getDataUrl(), indexDownload.getAuditMark(), byId.getDataDescribe());
        }

        return list.isEmpty() ? ResultsVO.fail("0") : ResultsVO.succeed(poGetAuditMark);
    }

    @Operation(summary = "获取审核状态")
    @Parameters({
            @Parameter(name = "id", description = "用户名id", required = false),
    })
    @RequestMapping(value = "/getAuditMarkList", method = RequestMethod.GET)
    public ResultsVO getAuditMarkList(ReGetAuditMark reGetAuditMark) {

        QueryWrapper<IndexDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", reGetAuditMark.user_id);
        queryWrapper.eq("data_index1", reGetAuditMark.data_index1);
        List<IndexDownload> list = indexDownloadService.list(queryWrapper);
        PoGetAuditMark poGetAuditMark = null;
        ArrayList<PoGetAuditMark> arrayList = new ArrayList<>();
        if (!list.isEmpty()) {
            for (IndexDownload indexDownload : list) {
                DataDownload byId = dataDownloadService.getById(indexDownload.getDataDownloadId());
                poGetAuditMark = new PoGetAuditMark(byId.getDataName(), byId.getDataUrl(), indexDownload.getAuditMark(), byId.getDataDescribe());
                arrayList.add(poGetAuditMark);
            }
        }

        return list.isEmpty() ? ResultsVO.fail("0") : ResultsVO.succeed(arrayList);
    }


    @Operation(summary = "资料下载接口")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页码", required = false),
            @Parameter(name = "pageSize", description = "页码大小", required = false),
            @Parameter(name = "role", description = "0 管理员 1 教师 2 学生")
    })
    @RequestMapping(value = "/getDownloadListByTeacher", method = RequestMethod.GET)
    public ResultsVO getDownloadListByTeacher(
            RqPage rqPage
    ) {
        List<DataDownloadServiceImpl.RpDownloadListByTeacher> downloadListByTeacher = dataDownloadService.getDownloadListByTeacher(rqPage);
        int total = downloadListByTeacher.size(), limitA, limitB;
        limitA = (rqPage.getPageNum() - 1) * rqPage.getPageNum();
        limitB = rqPage.getPageNum() * rqPage.getPageSize() - 1;
        if (limitB > total) {
            limitB = total;
        }
        ArrayList<DataDownloadServiceImpl.RpDownloadListByTeacher> test_list_2 = new ArrayList<>(downloadListByTeacher.subList(limitA, limitB));
        String s = String.valueOf(downloadListByTeacher.size());
        return ResultsVO.succeed(s, test_list_2);
    }

    record RqPutAuditMark(Integer id, Integer auditMark, Integer studentId, Integer studentState,
                          Integer index1, Integer index2) {
    }

    @Operation(summary = "资料下载接口")
    @Parameters({
            @Parameter(name = "id", description = "id", required = false),
            @Parameter(name = "auditMark", description = "状态", required = false),
    })
    @RequestMapping(value = "/putAuditMark", method = RequestMethod.PUT)
    public ResultsVO putAuditMark(@RequestBody RqPutAuditMark rqPutAuditMark) {
        IndexDownload indexDownload = new IndexDownload();
        indexDownload.setId(rqPutAuditMark.id);
        indexDownload.setAuditMark(rqPutAuditMark.auditMark);
        boolean b = indexDownloadService.updateById(indexDownload);
        Student student = new Student();
        student.setId(rqPutAuditMark.studentId);
        student.setStudentState(rqPutAuditMark.studentState);
        boolean b1 = studentService.updateById(student);

        return ResultsVO.succeed(b == b1);
    }


    @Operation(summary = "资料下载接口")
    @Parameters({
            @Parameter(name = "userId", description = "学生id", required = false),
            @Parameter(name = "dataIndex1", description = "状态", required = false),
            @Parameter(name = "dataIndex2", description = "状态", required = false),
    })
    @RequestMapping(value = "/getDownloadFileName", method = RequestMethod.GET)
    public ResultsVO getDownloadFileName(@RequestParam Integer userId, @RequestParam Integer dataIndex1, @RequestParam Integer dataIndex2) {
        QueryWrapper<IndexDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("data_index1", dataIndex1);
        List<IndexDownload> indexDownloads = indexDownloadService.list(queryWrapper);
        if (indexDownloads.isEmpty()) {
            return ResultsVO.fail("没找到");
        }
        if (indexDownloads.size() == 1) {
            IndexDownload indexDownload = indexDownloads.get(0);
            DataDownload byId = dataDownloadService.getById(indexDownload.getDataDownloadId());
            return ResultsVO.succeed(byId.getDataName());
        }
        List<String> strings = new ArrayList<>();
        indexDownloads.forEach(indexDownload -> {
            DataDownload byId = dataDownloadService.getById(indexDownload.getDataDownloadId());
            strings.add(byId.getDataName());
        });
        return ResultsVO.succeed(strings);
    }
}
