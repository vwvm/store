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
@ApiModel(value = "Student对象", description = "用于记录教师的详细信息")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("外键，用户表，id")
    private Integer userId;

    @ApiModelProperty("外键，学科专业表，id")
    private Integer majorId;

    @ApiModelProperty("外键，教师表，id, 导师名字")
    private Integer teacherId;

    @ApiModelProperty("学生名字")
    private String studentName;

    @ApiModelProperty("学生描述")
    private String studentDescribe;

    @ApiModelProperty("学生所在班级")
    private String studentClass;

    @ApiModelProperty("学生成绩")
    private Integer studentScore;

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

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDescribe() {
        return studentDescribe;
    }

    public void setStudentDescribe(String studentDescribe) {
        this.studentDescribe = studentDescribe;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Integer studentScore) {
        this.studentScore = studentScore;
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
        return "Student{" +
            "id = " + id +
            ", userId = " + userId +
            ", majorId = " + majorId +
            ", teacherId = " + teacherId +
            ", studentName = " + studentName +
            ", studentDescribe = " + studentDescribe +
            ", studentClass = " + studentClass +
            ", studentScore = " + studentScore +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
