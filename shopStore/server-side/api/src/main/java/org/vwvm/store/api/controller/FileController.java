package org.vwvm.store.api.controller;

import io.minio.messages.Bucket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vwvm.store.api.config.MinioConfig;
import org.vwvm.store.api.config.MinioUtil;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(tags = "文件相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/product/file")
public class FileController {


    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

    @ApiOperation(value = "查看存储bucket是否存在")
    @GetMapping("/bucketExists")
    public ResultVO bucketExists(@RequestParam("bucketName") String bucketName) {
        return new ResultVO(ResStatus.OK, "bucketName", minioUtil.bucketExists(bucketName));
    }

    @ApiOperation(value = "创建存储bucket")
    @GetMapping("/makeBucket")
    public ResultVO makeBucket(String bucketName) {
        return new ResultVO(ResStatus.OK, "bucketName", minioUtil.makeBucket(bucketName));
    }

    @ApiOperation(value = "删除存储bucket")
    @GetMapping("/removeBucket")
    public ResultVO removeBucket(String bucketName) {
        return new ResultVO(ResStatus.OK, "bucketName", minioUtil.removeBucket(bucketName));
    }

    @ApiOperation(value = "获取全部bucket")
    @GetMapping("/getAllBuckets")
    public ResultVO getAllBuckets() {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        return new ResultVO(ResStatus.OK, "allBuckets", allBuckets);
    }

    @ApiOperation(value = "文件上传返回url")
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return new ResultVO(ResStatus.OK, "url", (prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName));
        }
        return new ResultVO(ResStatus.NO, null, null);
    }

    @ApiOperation(value = "图片/视频预览")
    @GetMapping("/preview")
    public ResultVO preview(@RequestParam("fileName") String fileName) {
        return new ResultVO(ResStatus.OK, "filleName", minioUtil.preview(fileName));
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public ResultVO download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName, res);
        return new ResultVO(ResStatus.OK, null, res);
    }

    @ApiOperation(value = "删除文件", notes = "根据url地址删除文件")
    @PostMapping("/delete")
    public ResultVO remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName() + "/") + prop.getBucketName().length() + 1);
        minioUtil.remove(objName);
        return new ResultVO(ResStatus.OK, "objName", objName);
    }

    @ApiOperation(value = "获取全部object")
    @GetMapping("/getListObjects")
    public ResultVO getListObjects() {

        return new ResultVO(ResStatus.OK, "allBuckets", minioUtil.listObjects());
    }
}
