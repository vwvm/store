package org.vwvm.paperManagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 资源下载信息索引
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "IndexDownload对象", description = "下载信息表")
@ApiModel(value = "IndexDownload对象", description = "资源下载信息索引")
public class IndexDownload implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("外键，用户表，id")
    private String userId;

    @ApiModelProperty("一级标签，表示过程")
    private String dataIndex1;

    @Schema(name = "状态信息", description = "0 审核通过, 1 待待审核, 2 审核不通过")
    private Integer auditMark;

    @ApiModelProperty("二级标签，过程表中的序号")
    private String dataIndex2;

    @ApiModelProperty("外键，下载信息表，id")
    private String dataDownloadId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataIndex1() {
        return dataIndex1;
    }

    public void setDataIndex1(String dataIndex1) {
        this.dataIndex1 = dataIndex1;
    }

    public String getDataIndex2() {
        return dataIndex2;
    }

    public void setDataIndex2(String dataIndex2) {
        this.dataIndex2 = dataIndex2;
    }

    public String getDataDownloadId() {
        return dataDownloadId;
    }

    public void setDataDownloadId(String dataDownloadId) {
        this.dataDownloadId = dataDownloadId;
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

}
