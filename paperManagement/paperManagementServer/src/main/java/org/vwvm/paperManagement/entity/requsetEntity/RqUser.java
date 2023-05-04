package org.vwvm.paperManagement.entity.requsetEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h3>store</h3>
 * <p>用于接收请求的User实体</p>
 *
 * @author : BlackBox
 * @date : 2023-04-19 10:26
 **/
@Data
@AllArgsConstructor
public class RqUser {

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名，可用于登录")
    private String userUsername;

    @ApiModelProperty("用户密码，可用于登录")
    private String userPassword;

    @ApiModelProperty("用户电话，可用于登录")
    private String userTelephone;

    @ApiModelProperty("用户邮箱，可用于登录")
    private String userEmail;

    @Schema(description = "用户角色，json数据, list")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> user_roles = List.of("ROLE_user");

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Integer updateUserId;

    @ApiModelProperty("删除标记")
    private Boolean deleteFlag;
}
