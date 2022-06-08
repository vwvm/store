package org.blackbox.store.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User对象", description = "用户信息")
@TableName("user")
public class User {

    @ApiModelProperty(dataType = "int", required = false)
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(dataType = "String", required = false)
    @TableField("username")
    private String userName;

    @ApiModelProperty(dataType = "String", required = false)
    private String password;

    @ApiModelProperty(dataType = "String", required = false)
    @TableField("img")
    private String userImg;

    @ApiModelProperty(dataType = "int", required = false)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(dataType = "int", required = false)
    private Integer age;
}
