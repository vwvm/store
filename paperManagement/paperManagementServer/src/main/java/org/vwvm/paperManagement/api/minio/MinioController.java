package org.vwvm.paperManagement.api.minio;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vwvm.paperManagement.commons.utils.MinioUtil;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.DataDownload;
import org.vwvm.paperManagement.entity.IndexDownload;
import org.vwvm.paperManagement.service.impl.DataDownloadServiceImpl;
import org.vwvm.paperManagement.service.impl.IndexDownloadServiceImpl;

/**
 * <h3>store</h3>
 * <p>minio相关控制类</p>
 *
 * @author : BlackBox
 * @date : 2023-04-24 23:05
 **/
@Controller
@CrossOrigin
@ResponseBody
@Tag(name = "minio接口", description = "minio所有的接口")
@RequestMapping("/minio")
public class MinioController {

    private final MinioUtil minioUtil;
    private final IndexDownloadServiceImpl indexDownloadService;
    private final DataDownloadServiceImpl dataDownloadService;

    @Autowired
    public MinioController(MinioUtil minioUtil, IndexDownloadServiceImpl indexDownloadService, DataDownloadServiceImpl dataDownloadService) {
        this.minioUtil = minioUtil;
        this.indexDownloadService = indexDownloadService;
        this.dataDownloadService = dataDownloadService;
    }

    record ReUpload(String user_id, String data_index1, String data_index2, String data_name, String data_describe) {

    }

    @Operation(summary = "minio上传测试")
    @ResponseBody
    @PostMapping("/upload")
    public ResultsVO upload(
            ReUpload reUpload,
            @RequestParam(name = "multipartFile") MultipartFile multipartFile) {
        String headerStr = reUpload.user_id + reUpload.data_index1 + reUpload.data_index2 + "&";
        String endStr = "&" + System.currentTimeMillis();
        String upload = minioUtil.upload(multipartFile, headerStr, endStr);
        DataDownload dataDownload = new DataDownload();
        IndexDownload indexDownload = new IndexDownload();
        dataDownload.setDataUrl(upload);
        dataDownload.setDataName(reUpload.data_name);
        dataDownload.setDataDescribe(reUpload.data_describe);
        QueryWrapper<DataDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_url", upload);
        if (dataDownloadService.save(dataDownload)) {
            dataDownload = dataDownloadService.getOne(queryWrapper);
        }
        indexDownload.setUserId(reUpload.user_id);
        indexDownload.setDataIndex1(reUpload.data_index1);
        indexDownload.setDataIndex2(reUpload.data_index2);
        indexDownload.setDataDownloadId(dataDownload.getId().toString());
        boolean save = indexDownloadService.save(indexDownload);
        return save ? ResultsVO.succeed(upload) : ResultsVO.fail("失败");
    }

    @Operation(summary = "minio下载测试")
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName) {
        return minioUtil.download(fileName);
    }

    @Operation(summary = "minio删除文件")
    @GetMapping("/delete")
    public ResultsVO delete(@RequestParam String fileName) {
        return ResultsVO.succeed(minioUtil.removeObject(fileName));
    }

    @Operation(summary = "minio创建桶")
    @PostMapping("/existBucket")
    public void existBucket(@RequestParam String bucketName) {
        minioUtil.existBucket(bucketName);
    }
}
