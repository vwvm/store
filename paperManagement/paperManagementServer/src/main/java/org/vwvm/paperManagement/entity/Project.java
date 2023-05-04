package org.vwvm.paperManagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 课题信息表
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-07
 */
@Schema(name = "Project对象", description = "课题信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键,课题id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "课题名")
    private String projectName;

    @Schema(description = "课题表述")
    private String projectDescribe;

    @Schema(description = "课题来源（老师建议等等）")
    private String projectFrom;

    @Schema(description = "外键,导师id")
    private String teacherId;

    @Schema(description = "教师名字")
    @TableField(exist = false)
    private String teacherName;

    @Schema(description = "外键,学生id")
    private String studentId;

    @Schema(description = "学生名字")
    @TableField(exist = false)
    private String studentName;

    @Schema(description = "该选题的状态，默认状态为0,该课题可选；为1时，待审核，不可选；选题状态2，已被选择，不可选。")
    private Integer projectState;

    @Schema(description = "创建时间")
    private LocalDateTime creationTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人id")
    private String updateUserId;

    @Schema(description = "删除标记")
    private Boolean deleteFlag;

    public Project(String projectName, String projectDescribe, String projectFrom) {
        this.projectName = projectName;
        this.projectDescribe = projectDescribe;
        this.projectFrom = projectFrom;
    }

    public Project(Integer id, String projectName, String projectDescribe, String projectFrom) {
        this(projectName, projectDescribe, projectFrom);
        this.id = id;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public String getProjectFrom() {
        return projectFrom;
    }

    public void setProjectFrom(String projectFrom) {
        this.projectFrom = projectFrom;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }



}
