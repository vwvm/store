package org.vwvm.paperManagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 资源下载信息信息
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-03
 */
@TableName("data_download")
@ApiModel(value = "DataDownload对象", description = "资源下载信息信息")
public class DataDownload implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("资源名称")
    private String dataName;

    @ApiModelProperty("资源描述")
    private String dataDescribe;

    @ApiModelProperty("资源下载路径")
    private String dataUrl;

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private String updateUserId;

    @ApiModelProperty("删除标记")
    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataDescribe() {
        return dataDescribe;
    }

    public void setDataDescribe(String dataDescribe) {
        this.dataDescribe = dataDescribe;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "DataDownload{" +
            "id = " + id +
            ", dataName = " + dataName +
            ", dataDescribe = " + dataDescribe +
            ", dataUrl = " + dataUrl +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
