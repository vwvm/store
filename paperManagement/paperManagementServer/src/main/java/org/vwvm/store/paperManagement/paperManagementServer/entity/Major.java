package org.vwvm.store.paperManagement.paperManagementServer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用于记录学科专业信息
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@ApiModel(value = "Major对象", description = "用于记录学科专业信息")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("外键，学校，id")
    private Integer schoolId;

    @ApiModelProperty("名称")
    private String majorName;

    @ApiModelProperty("描述")
    private String majorDescribe;

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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorDescribe() {
        return majorDescribe;
    }

    public void setMajorDescribe(String majorDescribe) {
        this.majorDescribe = majorDescribe;
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
        return "Major{" +
            "id = " + id +
            ", schoolId = " + schoolId +
            ", majorName = " + majorName +
            ", majorDescribe = " + majorDescribe +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
