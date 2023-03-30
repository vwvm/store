package org.vwvm.store.paperManagement.paperManagementServer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用于记录教师的详细信息
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@ApiModel(value = "Teacher对象", description = "用于记录教师的详细信息")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("外键，用户表，id")
    private Integer userId;

    @ApiModelProperty("外键，部门表，id")
    private Integer departmentId;

    @ApiModelProperty("教师名字")
    private String teacherName;

    @ApiModelProperty("教师描述")
    private String teacherDescribe;

    @ApiModelProperty("教师管理课题数")
    private Integer teacherProjectNum;

    @ApiModelProperty("教师职称")
    private String teacherRank;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDescribe() {
        return teacherDescribe;
    }

    public void setTeacherDescribe(String teacherDescribe) {
        this.teacherDescribe = teacherDescribe;
    }

    public Integer getTeacherProjectNum() {
        return teacherProjectNum;
    }

    public void setTeacherProjectNum(Integer teacherProjectNum) {
        this.teacherProjectNum = teacherProjectNum;
    }

    public String getTeacherRank() {
        return teacherRank;
    }

    public void setTeacherRank(String teacherRank) {
        this.teacherRank = teacherRank;
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
        return "Teacher{" +
            "id = " + id +
            ", userId = " + userId +
            ", departmentId = " + departmentId +
            ", teacherName = " + teacherName +
            ", teacherDescribe = " + teacherDescribe +
            ", teacherProjectNum = " + teacherProjectNum +
            ", teacherRank = " + teacherRank +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
