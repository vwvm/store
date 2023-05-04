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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用于记录教师的详细信息
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Student对象", description = "用于记录教师的详细信息")
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("外键，用户表，id")
    private Integer userId;

    @ApiModelProperty("外键，学科专业表，id")
    private Integer majorId;

    @Schema(description = "外键，教师表，id")
    private String teacherId;

    @Schema(description = "状态")
    private Integer studentState;

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

    @Schema(description = "学科专业名称")
    @TableField(exist = false)
    private String majorName;
}
